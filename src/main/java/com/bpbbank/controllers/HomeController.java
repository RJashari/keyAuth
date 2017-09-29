package com.bpbbank.controllers;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.bpbbank.Dega;
import com.bpbbank.service.MeyAuthenticationService;



@Controller
public class HomeController {

	@Autowired
	MeyAuthenticationService service;
	
	@GetMapping("/login")
	public String getLogin() {
		
		return "login";
	}

	@GetMapping("/home")
	public String getHome() {
		
		System.out.println("AKAKUNKAKA");
		return "s";
	}

	@GetMapping("/addKey")
	public String getAddKey(Model model) {

		model.addAttribute("dega", new Dega());
		return "addKey";

	}
	@PostMapping("/addKey")
	public String keySubmit(@ModelAttribute Dega dega, Model model) {
		service.add(dega);
		model.addAttribute("dega",  dega);
		System.out.println("key report: " + dega.getDega());
		return "result";
	}
	

}
