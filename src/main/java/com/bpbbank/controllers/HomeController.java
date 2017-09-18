package com.bpbbank.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/home")
	public String getHome() {
		System.out.println("AKAKUNKAKA");
		return "home1";
	}
}
