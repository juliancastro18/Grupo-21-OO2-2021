package com.unla.grupo21.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Min;

import com.unla.grupo21.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.unla.grupo21.helpers.ViewRouteHelper;
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

	@PreAuthorize("hasRole('ROLE_AUDITOR')")
	@PostMapping("/activeDates")
	public ModelAndView activeBetweenDates(@ModelAttribute(name = "model") BuscarModel model) {

		ModelAndView mAV = new ModelAndView(ViewRouteHelper.PERMISO_DATES);

		List<PermisoModel> lstPermisos = permisoService.getAllBetweenDates(model.getStartDate(), model.getEndDate());

		mAV.addObject("lstPermisos", lstPermisos);

		return mAV;
	}

	@PreAuthorize("hasRole('ROLE_AUDITOR')")
	@PostMapping("/activeDatesPlaces")
	public ModelAndView activeBetweenDatesAndPlaces(@ModelAttribute(name = "model") BuscarModel model) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.PERMISO_DATES);
		LugarModel lugar = lugarService.findById(model.getLugarModel());

		List<PermisoModel> lstPermisos = permisoService.getAllBetweenDatesAndPlaces(model.getStartDate(), model.getEndDate(), lugar, model.isDesde());

		mAV.addObject("lstPermisos", lstPermisos);

		return mAV;
	}

	@PreAuthorize("hasRole('ROLE_AUDITOR')")
	@GetMapping("/buscar")
	public ModelAndView search() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.PERMISO_BUSCAR);
		BuscarModel buscar = new BuscarModel();
		buscar.setLugaresModel(lugarService.getAll());

		mAV.addObject("model", buscar);

		return mAV;
	}
}
