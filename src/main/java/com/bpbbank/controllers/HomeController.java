package com.bpbbank.controllers;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.bpbbank.dao.CrudDao;

import com.bpbbank.Dega;
import com.bpbbank.Service;



@Controller
public class HomeController {

//	@Autowired
	Service service;
	@Autowired
	CrudDao crudDao;
	
	@GetMapping("/login")
	public String getLogin() {
		
		return "login";
	}

	@GetMapping({"/", "/home"})
	public String getHome(Model model) {
		model.addAttribute("dega", new Dega());
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
		crudDao.save(dega);
		model.addAttribute("dega",  dega);
		System.out.println("dega : " + dega.getDega());
		return "s";
	}
	
	

}
