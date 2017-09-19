package com.bpbbank.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/home")
	public String getHome(Model model) {
                model.addAttribute("listaEcelesave", "RINOR");
		System.out.println("AKAKUNKAKA");
		return "s";
	}
        @GetMapping("/addKey")
        public String getAddKey(Model model){
            
            model.addAttribute("key","Celesi1");
            return "addKey";
        }
}
