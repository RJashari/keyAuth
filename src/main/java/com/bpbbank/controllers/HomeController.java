package com.bpbbank.controllers;




import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bpbbank.dao.CrudDao;

import antlr.collections.List;

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
	public String getHome(Model model, Dega dega) {
		
		model.addAttribute("deget",  crudDao.getAllDeget());
		crudDao.getAllDeget();
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
//		model.addAttribute("dega",  dega);
//		model.addAttribute("nrKolones", dega.getNrKolones()+1);
		model.addAttribute("nrKolones", dega.getId());
		model.addAttribute("deget",  crudDao.getAllDeget());
		System.out.println("dega : " + dega.getDega());
		return "s";
	}
	@RequestMapping(method = RequestMethod.GET, path="/fshijDegen")
	public String handleDeleteDega(@RequestParam("id") long id) {
		System.out.println("na ke myt");
	    crudDao.remove(id);
	    System.out.println("u fshi");
	    return "redirect:/home";
	}
	@GetMapping("/updateKey")
	public String getUpdateKey(@RequestParam("id") long id, Model model) {

		model.addAttribute("dega", crudDao.getByID(id));
		return "updateKey";

	}
	@PostMapping("/updateKey")
	public String keyUpdate(@ModelAttribute Dega dega, Model model) {
		System.out.println("na ke myt22");
		System.out.println("dega id: " + dega.getId() + " " + dega.getCelesiIHyrjesDege() + " " + dega.getCelesiIDeresAtm());
		crudDao.update(dega);
		System.out.println("U MODIFIKU");
		return "redirect:/home";
	}
	@RequestMapping(method = RequestMethod.GET, path="/modifikoDegen")
	public String handleModifyDege(@RequestParam("id") long id) {
		System.out.println("sssna ke myt");
	    crudDao.update(id);
	    System.out.println("u modifiku");
	    return "redirect:/updateKey";
	}
	
	

}
