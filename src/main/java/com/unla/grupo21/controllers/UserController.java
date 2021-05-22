package com.unla.grupo21.controllers;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.unla.grupo21.helpers.ViewRouteHelper;
import com.unla.grupo21.models.TipoDocumento;
import com.unla.grupo21.models.UserModel;
import com.unla.grupo21.models.UserRoleModel;
import com.unla.grupo21.services.IUserRoleService;
import com.unla.grupo21.services.IUserService;


@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	@Qualifier("userService")
	private IUserService userService;
	
	@Autowired
	@Qualifier("userRoleService")
	private IUserRoleService userRoleService;
	
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
	
	//TODO agregar autorizacion para admin
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/new")
	public ModelAndView agregar() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.USER_NEW);
		List<TipoDocumento> lstTipoDoc = Arrays.asList(TipoDocumento.values());
		UserModel um = new UserModel();
		um.setActivo(true);
		mAV.addObject("user", um);
		mAV.addObject("lstTipoDoc", lstTipoDoc);
		mAV.addObject("userroles", userRoleService.getAll());
		return mAV;
	}
	
	//TODO agregar autorizacion para admin
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/new")
	public RedirectView create(@Valid @ModelAttribute("user") UserModel userModel, @RequestParam(name="idUserRole", required=true) String idUserRoleStr, BindingResult bindingResult) {
		RedirectView rV = new RedirectView();

		if(bindingResult.hasErrors()) {
			rV.setUrl(ViewRouteHelper.USER_NEW_INDEX);
		} else {
			rV.setUrl(ViewRouteHelper.USER_ABM_INDEX);

			int idUserRole = Integer.parseInt(idUserRoleStr);
			UserRoleModel urm = userRoleService.findById(idUserRole);
			userModel.setUserRole(urm);
			userService.insertOrUpdate(userModel);
		}
		return rV;
	}
	
}
