package com.unla.grupo21.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.unla.grupo21.helpers.ViewRouteHelper;
import com.unla.grupo21.models.UserRoleModel;
import com.unla.grupo21.services.IUserRoleService;

@Controller
@RequestMapping("/userRole")
public class UserRoleController {
	@Autowired
	@Qualifier("userRoleService")
	private IUserRoleService userRoleService;
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_AUDITOR')")
	@GetMapping("")
	public ModelAndView index()
	{
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.USERROLE_LIST);
		mAV.addObject("userRoles", userRoleService.getAll());
		mAV.addObject("userRole", new UserRoleModel());
		return mAV;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/abm")
	public ModelAndView abm()
	{
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.USERROLE_ABM);
		mAV.addObject("userRoles", userRoleService.getAll());
		mAV.addObject("abm", true);
		return mAV;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/new")
	public ModelAndView agregar()
	{
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.USERROLE_NEW);
		mAV.addObject("userRole", new UserRoleModel());
		return mAV;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/newUserRole")
	public ModelAndView create(@ModelAttribute("userRole") UserRoleModel userRoleModel)
	{
		ModelAndView mAV = new ModelAndView(new RedirectView(ViewRouteHelper.USERROLE_ABM_INDEX));
		userRoleService.insertOrUpdate(userRoleModel);
		return mAV;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable int id)
	{
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.USERROLE_EDIT);
		mAV.addObject("userRole", userRoleService.findById(id));
		mAV.addObject("edit", true);
		return mAV;
	}
}


















