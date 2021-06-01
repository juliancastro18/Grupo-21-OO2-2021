package com.unla.grupo21.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.unla.grupo21.helpers.Funciones;
import com.unla.grupo21.helpers.ViewRouteHelper;
import com.unla.grupo21.models.BuscarModel;
import com.unla.grupo21.models.LugarModel;
import com.unla.grupo21.models.PermisoModel;
import com.unla.grupo21.services.ILugarService;
import com.unla.grupo21.services.IPermisoService;
import com.unla.grupo21.services.IPersonaService;
import com.unla.grupo21.services.IRodadoService;

@Controller
@RequestMapping("/permiso/buscar")
public class SearchController {
	
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
	
	
	@PreAuthorize("hasRole('ROLE_AUDITOR')")
	@GetMapping("/porfechas")
	public ModelAndView search() {
		
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.PERMISO_BUSCAR);
		BuscarModel buscar = new BuscarModel();
		buscar.setLugaresModel(lugarService.getAllOrderByLugar());
		mAV.addObject("model", buscar);

		return mAV;
	}
	
	@PreAuthorize("hasRole('ROLE_AUDITOR')")
	@PostMapping("/activeDates")
	public ModelAndView activeBetweenDates(@ModelAttribute(name = "model") BuscarModel model){

		ModelAndView mAV = new ModelAndView(ViewRouteHelper.PERMISO_RESULTS);
		List<PermisoModel> lstPermisos = permisoService.getAllBetweenDates(model.getStartDate(), model.getEndDate());
		mAV.addObject("lstPermisos", lstPermisos);
		mAV.addObject("searchDesc", "Permisos activos entre " + Funciones.traerFechaCorta(model.getStartDate()) + " y " + Funciones.traerFechaCorta(model.getEndDate()));

		return mAV;
	}

	@PreAuthorize("hasRole('ROLE_AUDITOR')")
	@PostMapping("/activeDatesPlaces")
	public ModelAndView activeBetweenDatesAndPlaces(@ModelAttribute(name = "model") BuscarModel model) {
		
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.PERMISO_RESULTS);
		LugarModel lugar = lugarService.findById(model.getLugarModel());
		List<PermisoModel> lstPermisos = permisoService.getAllBetweenDatesAndPlaces(model.getStartDate(), model.getEndDate(), lugar, model.isDesde());
		mAV.addObject("lstPermisos", lstPermisos);
		mAV.addObject("searchDesc", "Permisos activos entre " + Funciones.traerFechaCorta(model.getStartDate())  + " y " + Funciones.traerFechaCorta(model.getEndDate())
					  + " con " + (model.isDesde() ? "origen en " : "destino a ") + StringUtils.capitalize(lugar.getLugar().toLowerCase()));

		return mAV;
	}

	
}
