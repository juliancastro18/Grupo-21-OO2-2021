package com.unla.grupo21.controllers;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.unla.grupo21.helpers.ViewRouteHelper;
import com.unla.grupo21.models.LugarModel;
import com.unla.grupo21.models.PermisoDiarioModel;
import com.unla.grupo21.models.PermisoModel;
import com.unla.grupo21.models.PermisoPeriodoModel;
import com.unla.grupo21.models.PermisoPreFormModel;
import com.unla.grupo21.models.PersonaModel;
import com.unla.grupo21.models.RodadoModel;
import com.unla.grupo21.models.TipoDocumento;
import com.unla.grupo21.models.UserModel;
import com.unla.grupo21.models.UserRoleModel;
import com.unla.grupo21.services.ILugarService;
import com.unla.grupo21.services.IPermisoService;
import com.unla.grupo21.services.IPersonaService;
import com.unla.grupo21.services.IRodadoService;

@Controller
@RequestMapping("/permiso")
public class PermisoController {

	@Autowired
	@Qualifier("permisoService")
	private IPermisoService permisoService;
	
	@Autowired
	@Qualifier("lugarService")
	private ILugarService lugarService;
	
	@Autowired
	@Qualifier("personaService")
	private IPersonaService personaService;
	
	@Autowired
	@Qualifier("rodadoService")
	private IRodadoService rodadoService;
	
	@GetMapping("/new")
	public ModelAndView solicitud() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.PERMISO_NEW);
	
		List<TipoDocumento> lstTipoDoc = Arrays.asList(TipoDocumento.values());
		mAV.addObject("lstTipoDoc", lstTipoDoc);
		mAV.addObject("preform", new PermisoPreFormModel());

		return mAV;
	}
	
	@PostMapping("/newpermiso")
	public ModelAndView create(@Valid @ModelAttribute("preform") PermisoPreFormModel preFormModel, BindingResult bindingResult) {
		ModelAndView mV;
		
		if(preFormModel.isEsDiario() == false && preFormModel.getDominio().isEmpty()) { //si se seleccionó permiso periodo y no se indicó patente
		    FieldError error = new FieldError("preform", "dominio", "Los permisos para períodos deben asignarse a rodados");
			bindingResult.addError(error);
		}
		
		
		if(bindingResult.hasErrors()) {
			
			mV = new ModelAndView(ViewRouteHelper.PERMISO_NEW);
			List<TipoDocumento> lstTipoDoc = Arrays.asList(TipoDocumento.values());
			mV.addObject("lstTipoDoc", lstTipoDoc);
			
		} else {
			
			mV = new ModelAndView(ViewRouteHelper.PERMISO_FORMULARIO);
			// si no hay errores instancio un nuevo permiso de la clase hija correspondiente
			PermisoModel nuevoPermiso = null;
			if(preFormModel.isEsDiario()) {
				nuevoPermiso = new PermisoDiarioModel();
			}else {
				nuevoPermiso = new PermisoPeriodoModel();
				//si encuentra el rodado, se lo asigno al nuevo permiso, sino le asigno solo la patente
				RodadoModel rm = rodadoService.findByDominio(preFormModel.getDominio());

				if(rm!=null) {
					((PermisoPeriodoModel)nuevoPermiso).setRodado(rm);
				}else {
					((PermisoPeriodoModel)nuevoPermiso).getRodado().setDominio(preFormModel.getDominio().toUpperCase());
				}
			}
			
			//lo mismo para la persona: si la encuentro se la asigno, sino le completo el campo DNI			
			PersonaModel pm = personaService.findByTipoDocumentoAndDocumento(preFormModel.getTipoDocumento(), preFormModel.getDocumento());
			if(pm!=null) {
				nuevoPermiso.setPedido(pm);
			}else {
				nuevoPermiso.getPedido().setTipoDocumento(preFormModel.getTipoDocumento());
				nuevoPermiso.getPedido().setDocumento(preFormModel.getDocumento());
			}
			
			mV.addObject("permiso", nuevoPermiso);
			mV.addObject("esDiario", preFormModel.isEsDiario());
			mV.addObject("lstTipoDoc", Arrays.asList(TipoDocumento.values()));
			mV.addObject("lugares", lugarService.getAll());
			
		}
		
		return mV;
	}
	
	
	@PostMapping("/postpermisodiario")
	public ModelAndView create(@Valid @ModelAttribute("permiso") PermisoDiarioModel permisoDiarioModel, BindingResult bindingResult,
							   @RequestParam(name="idDesde", required=true) String idDesdeStr,
							   @RequestParam(name="idHasta", required=true) String idHastaStr) {
		ModelAndView mV;
		
		// si la fecha es anterior a hoy o despues de un año
		if ( permisoDiarioModel.getFecha().isBefore(LocalDate.now()) || permisoDiarioModel.getFecha().isAfter(LocalDate.now().plusYears(1)) ) {
			bindingResult.addError(new FieldError("permiso", "fecha", "La fecha es incorrecta"));
		}
		if(idDesdeStr.equals(idHastaStr)) {
			bindingResult.addError(new FieldError("permiso", "desdeHasta", "El lugar de origen y destino no puede ser el mismo"));
		}
		
		if(bindingResult.hasErrors()) {
			mV = new ModelAndView(ViewRouteHelper.PERMISO_FORMULARIO);
			mV.addObject("esDiario", true);
			mV.addObject("lstTipoDoc", Arrays.asList(TipoDocumento.values()));
			mV.addObject("lugares", lugarService.getAll());
			
		} else {
			mV = new ModelAndView(new RedirectView(ViewRouteHelper.PERMISO_SUCCESS_REDIRECT));
			
			permisoDiarioModel.agregarLugar(lugarService.findById(Integer.valueOf(idDesdeStr)));
			permisoDiarioModel.agregarLugar(lugarService.findById(Integer.valueOf(idHastaStr)));
			
			if(permisoDiarioModel.getPedido().getId() == 0) {
				PersonaModel pm = personaService.insertOrUpdate(permisoDiarioModel.getPedido());			
				permisoDiarioModel.setPedido(pm);
			} else {
				PersonaModel pm = personaService.findById(permisoDiarioModel.getPedido().getId() );
				permisoDiarioModel.setPedido(pm);
			}
			
			permisoService.insertOrUpdate(permisoDiarioModel);
		}
		
		return mV;
	}
	
	@PostMapping("/postpermisoperiodo")
	public ModelAndView create(@Valid @ModelAttribute("permiso") PermisoPeriodoModel permisoPeriodoModel, BindingResult bindingResult,
							   @RequestParam(name="idDesde", required=true) String idDesdeStr,
							   @RequestParam(name="idHasta", required=true) String idHastaStr) {
		ModelAndView mV;
		
		// si la fecha es anterior a hoy o despues de un año
		if ( permisoPeriodoModel.getFecha().isBefore(LocalDate.now()) || permisoPeriodoModel.getFecha().isAfter(LocalDate.now().plusYears(1)) ) {
			bindingResult.addError(new FieldError("permiso", "fecha", "La fecha es incorrecta"));
		}
		if(idDesdeStr.equals(idHastaStr)) {
			bindingResult.addError(new FieldError("permiso", "desdeHasta", "El lugar de origen y destino no puede ser el mismo"));
		}
		
		if(bindingResult.hasErrors()) {
			mV = new ModelAndView(ViewRouteHelper.PERMISO_FORMULARIO);
			mV.addObject("esDiario", false);
			mV.addObject("lstTipoDoc", Arrays.asList(TipoDocumento.values()));
			mV.addObject("lugares", lugarService.getAll());
			
		} else {
			mV = new ModelAndView(new RedirectView(ViewRouteHelper.PERMISO_SUCCESS_REDIRECT));
			
			permisoPeriodoModel.agregarLugar(lugarService.findById(Integer.valueOf(idDesdeStr)));
			permisoPeriodoModel.agregarLugar(lugarService.findById(Integer.valueOf(idHastaStr)));
			
			if(permisoPeriodoModel.getPedido().getId() == 0) {
				PersonaModel pm = personaService.insertOrUpdate(permisoPeriodoModel.getPedido());			
				permisoPeriodoModel.setPedido(pm);
			} else {
				PersonaModel pm = personaService.findById(permisoPeriodoModel.getPedido().getId() );
				permisoPeriodoModel.setPedido(pm);
			}
			
			System.out.println(permisoPeriodoModel.getRodado());
			RodadoModel rm = rodadoService.insertOrUpdate(permisoPeriodoModel.getRodado());
			permisoPeriodoModel.setRodado(rm);
			
			
			permisoService.insertOrUpdate(permisoPeriodoModel);
		}
		
		return mV;
	}
	
	@GetMapping("/success")
	public ModelAndView success() {
		return new ModelAndView(ViewRouteHelper.PERMISO_SUCCESS);
	}
	
}
