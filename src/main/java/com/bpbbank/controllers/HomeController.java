package com.bpbbank.controllers;




import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.bpbbank.Dega;



@Controller
public class HomeController {

	@GetMapping("/home")
	public String getHome(Model model) {
		model.addAttribute("dega", "Rinor");
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
		
		model.addAttribute("dega",  dega);
		System.out.println("key report: " + dega.getDega());
		return "result";
	}

}
