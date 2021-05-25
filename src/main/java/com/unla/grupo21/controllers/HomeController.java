package com.unla.grupo21.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.unla.grupo21.helpers.ViewRouteHelper;
import com.unla.grupo21.services.IUserRoleService;
import com.unla.grupo21.services.IUserService;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@Autowired
	@Qualifier("userService")
	private IUserService userService;
	
	@Autowired
	@Qualifier("userRoleService")
	private IUserRoleService userRoleService;
	
	
	@GetMapping("/index")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.INDEX);
		String username = "Anónimo";
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    username = authentication.getName();
		}
		modelAndView.addObject("username", username);
		modelAndView.addObject("userCount", userService.countByActivoTrue());
		modelAndView.addObject("userRoleCount", userRoleService.count());
		
		return modelAndView;
	}
	
	@GetMapping("")
	public RedirectView redirectToHomeIndex() {
		return new RedirectView(ViewRouteHelper.ROUTE_INDEX);
	}
	
}