package com.unla.grupo21.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.lowagie.text.DocumentException;
import com.unla.grupo21.exporters.UserPDFExporter;
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
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.USER_LIST);
		mAV.addObject("users", userService.getActivos());
		return mAV;
	}
	
	//TODO agregar autorizacion para admin
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/abm")
	public ModelAndView abm() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.USER_ABM);
		mAV.addObject("users", userService.getAll());
		mAV.addObject("abm", true);
		return mAV;
	}
	
	//TODO agregar autorizacion para admin
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/new")
	public ModelAndView agregar() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.USER_NEW);
		List<TipoDocumento> lstTipoDoc = Arrays.asList(TipoDocumento.values());
		mAV.addObject("user", new UserModel());
		mAV.addObject("lstTipoDoc", lstTipoDoc);
		mAV.addObject("userroles", userRoleService.getAll());
		return mAV;
	}
	
	//TODO agregar autorizacion para admin
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/newuser")
	public ModelAndView create(@Valid @ModelAttribute("user") UserModel userModel, BindingResult bindingResult,
							   @RequestParam(name="idUserRole", required=true) String idUserRoleStr,
							   @RequestParam(name="edit", required=false, defaultValue="false") boolean edit) {
		ModelAndView mV;
		
		if(bindingResult.hasErrors()) {
			mV = new ModelAndView(ViewRouteHelper.USER_NEW);
			List<TipoDocumento> lstTipoDoc = Arrays.asList(TipoDocumento.values());
			mV.addObject("lstTipoDoc", lstTipoDoc);
			mV.addObject("userroles", userRoleService.getAll());
			mV.addObject("edit", edit);
		} else {
			mV = new ModelAndView(new RedirectView(ViewRouteHelper.USER_ABM_INDEX));
			int idUserRole = Integer.parseInt(idUserRoleStr);
			UserRoleModel urm = userRoleService.findById(idUserRole);
			userModel.setUserRole(urm);
			userService.insertOrUpdate(userModel);
		}
		
		return mV;
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable int id) {
		ModelAndView mV = new ModelAndView(ViewRouteHelper.USER_EDIT);
		mV.addObject("user", userService.findById(id));
		List<TipoDocumento> lstTipoDoc = Arrays.asList(TipoDocumento.values());
		mV.addObject("lstTipoDoc", lstTipoDoc);
		mV.addObject("userroles", userRoleService.getAll());
		mV.addObject("edit", true);
		return mV;
	}
	
	@GetMapping("/delete/{id}")
	public RedirectView delete(@PathVariable int id) {
		RedirectView rV = new RedirectView(ViewRouteHelper.USER_ABM_INDEX);
		UserModel um = userService.findById(id);
		um.setActivo(!um.isActivo());
		userService.insertOrUpdate(um);
		return rV;
	}
	
    @GetMapping("/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
         
        List<UserModel> listUsers = userService.getActivos();
         
        UserPDFExporter exporter = new UserPDFExporter(listUsers);
        exporter.export(response);
         
    }
}
