package com.unla.grupo21.controllers;

import com.unla.grupo21.helpers.ViewRouteHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/general.html")
public class NavController {

    @GetMapping("")
    public String returnNav() {
        return ViewRouteHelper.NAV;
    }
}
