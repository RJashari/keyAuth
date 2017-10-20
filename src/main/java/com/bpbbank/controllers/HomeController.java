package com.bpbbank.controllers;




import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.security.Principal;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bpbbank.GjeneroPdf;
import com.bpbbank.dao.DegaDao;
import com.bpbbank.domain.Dega;
import com.bpbbank.service.DegaService;
import com.bpbbank.service.KeyAuthenticationUserService;



@Controller
public class HomeController {

	@Autowired
	KeyAuthenticationUserService userService;
	@Autowired
	DegaService degaService;
	@Autowired
	GjeneroPdf gjeneroPdf;
	
	@GetMapping("/login")
	public String getLogin() {
		
		return "login";
	}

	@GetMapping({"/", "/home"})
	public String getHome(Model model, Dega dega, Principal principal) {
		UserDetails userDetails = userService.loadUserByUsername(principal.getName());
		model.addAttribute("deget",  degaService.getAllDegetForUser(principal.getName()));
		
		SimpleGrantedAuthority adminAuthority = new SimpleGrantedAuthority("ADMIN");
		if(userDetails.getAuthorities().contains(adminAuthority)){
			model.addAttribute("deget",  degaService.getAllDeget());
		}
		return "s";
	}

	@GetMapping("/addKey")
	public String getAddKey(Model model) {
		model.addAttribute("dega", new Dega());
		return "addKey";

	}
	@PostMapping("/addKey")
	public String keySubmit(@ModelAttribute Dega dega, Model model) {
		degaService.save(dega);
		model.addAttribute("deget",  degaService.getAllDeget());
		return "redirect:/home";
	}
	@RequestMapping(method = RequestMethod.GET, path="/fshijDegen")
	public String handleDeleteDega(@RequestParam("id") long id) {
	    degaService.remove(id);
	    return "redirect:/home";
	}
	@GetMapping("/updateKey")
	public String getUpdateKey(@RequestParam("id") long id, Model model) {
		model.addAttribute("dega", degaService.getByID(id));
		return "updateKey";

	}
	@PostMapping("/updateKey")
	public String keyUpdate(@ModelAttribute Dega dega, Model model) throws FileNotFoundException, MalformedURLException, ParseException {
		degaService.update(dega);
		gjeneroPdf.gjeneroPdf(dega);
		return "redirect:/home";
	}
	@RequestMapping(method = RequestMethod.GET, path="/modifikoDegen")
	public String handleModifyDege(@RequestParam("id") long id, Dega dega) throws FileNotFoundException, MalformedURLException, ParseException {
		gjeneroPdf.gjeneroPdf(dega);
	    degaService.update(id);
	    return "redirect:/updateKey";
	}
}
