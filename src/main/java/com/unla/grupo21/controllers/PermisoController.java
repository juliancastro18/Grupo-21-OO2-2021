package com.unla.grupo21.controllers;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.unla.grupo21.helpers.ViewRouteHelper;
import com.unla.grupo21.models.LugarModel;
import com.unla.grupo21.models.PermisoDiarioModel;
import com.unla.grupo21.models.PermisoModel;
import com.unla.grupo21.models.PersonaModel;
import com.unla.grupo21.models.RodadoModel;
import com.unla.grupo21.models.TipoDocumento;
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
	
	//TODO eliminar este metodo cuando se termine de testear
	@GetMapping("insertarPrueba")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.USER_LIST);

		Set<LugarModel> lstLugares = new HashSet<LugarModel>();
		lstLugares.add(lugarService.findById(1));
		lstLugares.add(lugarService.findById(2));
		PersonaModel personaModel = new PersonaModel("Susana", "Roccasalvo", TipoDocumento.DNI, 22345691L, "susy_rock@gmail.com");
		
		PermisoModel permisoModel = new PermisoDiarioModel(personaModel, LocalDate.of(2021, 3, 13), lstLugares, "Trabajo escencial");
		
		System.out.println(permisoModel);
		permisoService.insertOrUpdate(permisoModel);
		
		// -----------
		
		Set<LugarModel> lstLugares2 = new HashSet<LugarModel>();
		lstLugares2.add(lugarService.findById(3));
		lstLugares2.add(lugarService.findById(4));
		
		PersonaModel personaModel2 = personaService.findByTipoDocumentoAndDocumento(TipoDocumento.DNI, 2345689L);
		
		return mAV;
	}
	
}
