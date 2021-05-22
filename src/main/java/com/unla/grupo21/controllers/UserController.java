package com.unla.grupo21.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.unla.grupo21.helpers.ViewRouteHelper;
import com.unla.grupo21.models.TipoDocumento;
import com.unla.grupo21.models.UserModel;
import com.unla.grupo21.services.IUserService;


@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	@Qualifier("userService")
	private IUserService userService;
	
	//TODO agregar autorizacion para admin y auditor
	//@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_EDITOR')")
	@GetMapping("")
	public ModelAndView index() {
		boolean abm = false;
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.USER_LIST);
		mAV.addObject("users", userService.getAll());
		mAV.addObject("abm", abm);
		return mAV;
	}
	
	//TODO agregar autorizacion para admin
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/abm")
	public ModelAndView abm() {
		boolean abm = true;
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.USER_ABM);
		mAV.addObject("users", userService.getAll());
		mAV.addObject("abm", abm);
		return mAV;
	}
	
	@GetMapping("/new")
	public ModelAndView agregar() {
		//TODO pasar por parametro tipos de documento y generar lista desplegable.
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.USER_NEW);
		mAV.addObject("users", userService.getAll());
		mAV.addObject("user", new UserModel());
		return mAV;
	}
	
	@PostMapping("/new")
	public RedirectView create(@ModelAttribute("user") UserModel userModel) {
		userService.insertOrUpdate(userModel);
		return new RedirectView(ViewRouteHelper.USER_ROOT);
	}
	
}
