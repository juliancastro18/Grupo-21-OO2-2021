package com.unla.grupo21.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
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

import com.lowagie.text.DocumentException;
import com.unla.grupo21.exporters.UserRolePDFExporter;
import com.unla.grupo21.helpers.ViewRouteHelper;
import com.unla.grupo21.models.UserRoleModel;
import com.unla.grupo21.services.IUserRoleService;

@Controller
@RequestMapping("/userrole")
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
	
	@PreAuthorize("hasRole('ROLE_AUDITOR')")
	@GetMapping("/pdf")
	public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
         
        List<UserRoleModel> listUserRoles = userRoleService.getAll();
         
        UserRolePDFExporter exporter = new UserRolePDFExporter(listUserRoles);
        exporter.export(response);
    }
}


















