package com.bpbbank.controllers;




import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.mail.NoSuchProviderException;

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
import org.springframework.web.servlet.ModelAndView;

import com.bpbbank.GjeneroAddPdf;
import com.bpbbank.GjeneroAllPdf;
import com.bpbbank.GjeneroPdf;
import com.bpbbank.GjeneroPdfDega;
import com.bpbbank.GjeneroPdfDelete;
import com.bpbbank.dao.DegaDao;
import com.bpbbank.domain.Dega;
import com.bpbbank.domain.KeyAuthenticationUser;
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
	@Autowired
	GjeneroPdfDega gjeneroPdfDega;
	@Autowired
	GjeneroAddPdf gjeneroAddPdf;
	@Autowired
	GjeneroAllPdf gjeneroAllPdf;
	@Autowired
	GjeneroPdfDelete gjeneroPdfDelete;
	
	public String krijimiDeges;
	@GetMapping("/login")
	public String getLogin() {
		
		return "login";
	}

	@GetMapping({"/", "/home"})
	public String getHome(Model model, Dega dega, Principal principal, KeyAuthenticationUser user) throws ParseException, IOException, NoSuchProviderException {
		UserDetails userDetails = userService.loadUserByUsername(principal.getName());
		model.addAttribute("deget",  degaService.getAllDegetForUser(principal.getName()));
		String username = principal.getName().trim();
		model.addAttribute("user", username);
		System.out.println("-------------------- username "+username);
		
		SimpleGrantedAuthority adminAuthority = new SimpleGrantedAuthority("ADMIN");
		if(userDetails.getAuthorities().contains(adminAuthority)){
			model.addAttribute("deget",  degaService.getAllDeget());

		}
		return "s";
	}

	@GetMapping("/gjeneroPdfDega")
	public String getGjeneroPdfDega(@RequestParam("id") long id, Model model,Dega dega, Principal principal,KeyAuthenticationUser user) throws NoSuchProviderException, ParseException, IOException {
		model.addAttribute("dega", degaService.getByID(id));
		dega = degaService.getByID(id);
		String username = principal.getName();
		user = userService.getByUsername(username);
		String email = user.getEmail();
		gjeneroPdfDega = new GjeneroPdfDega(username, email);
		gjeneroPdfDega.gjeneroPdf(dega);
		return "redirect:/home";

	}


	@GetMapping("/addKey")
	public String getAddKey(Model model) {
		model.addAttribute("dega", new Dega());
		return "addKey";

	}
	@PostMapping("/addKey")
	public String keySubmit(@ModelAttribute Dega dega, Model model, Principal principal,KeyAuthenticationUser user) throws ParseException, IOException, NoSuchProviderException {
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("HH:mm - dd/MM/yyyy");
		
		

		String date= ft.format(dNow);
		if(dega.getKrijimiDeges().isEmpty() || dega.getKrijimiDeges().equals("")) {
			dega.setKrijimiDeges(date);
			String krijimiDeges=dega.getKrijimiDeges();
			System.out.println("AAAAAAAA"+krijimiDeges);
			dega.setModifikimiDeges("Ska");
		}else {
			dega.setModifikimiDeges(date);
			
		}
		
		degaService.save(dega);
		String username = principal.getName();
		System.out.println("-----------------------username: "+username);
		user = userService.getByUsername(username);
		System.out.println("-----------------USER: "+user);
		String email  = user.getEmail();
		gjeneroAddPdf = new GjeneroAddPdf(username,email);
		gjeneroAddPdf.gjeneroAddPdf(dega);
		
		model.addAttribute("deget",  degaService.getAllDeget());
		return "redirect:/home";
	}
	@RequestMapping(method = RequestMethod.GET, path="/fshijDegen")
	public String handleDeleteDega(@RequestParam("id") long id,Dega dega, Principal principal, KeyAuthenticationUser user) throws NoSuchProviderException, ParseException, IOException {
		
		String username = principal.getName();
		user = userService.getByUsername(username);
		dega = degaService.getByID(id);
		gjeneroPdfDelete = new GjeneroPdfDelete(username, user.getEmail());
		gjeneroPdfDelete.gjeneroPdf(dega);
	    degaService.remove(id);
	    return "redirect:/home";
	}
	@GetMapping("/updateKey")
	public String getUpdateKey(@RequestParam("id") long id, Model model) {
		model.addAttribute("dega", degaService.getByID(id));
		return "updateKey";

	}
	@PostMapping("/updateKey")
	public String keyUpdate(@RequestParam("id") long id, @ModelAttribute Dega dega, Model model, Principal principal, KeyAuthenticationUser user) throws ParseException, IOException, NoSuchProviderException {
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("HH:mm - dd/MM/yyyy");
		Dega degaOld = new Dega();
		degaOld = degaService.getByID(id);
		String date= ft.format(dNow);
		dega.setKrijimiDeges(degaOld.getKrijimiDeges());
		dega.setModifikimiDeges(date);
//		dega.setKrijimiDeges(krijimiDeges);
		String username = principal.getName();
		user = userService.getByUsername(username);
		gjeneroPdf = new GjeneroPdf(username, user.getEmail());
		gjeneroPdf.gjeneroPdf(dega);
		degaService.update(dega);
		
		System.out.println("FIUUUUUUUUUUUUUU");
		
//		/*gjeneroPdf.sendReport(session, filename, extraMessage, user, password, principal, dega);*/
		return "redirect:/home";
	}
	@RequestMapping(method = RequestMethod.GET, path="/modifikoDegen")
	public String handleModifyDege(@RequestParam("id") long id, Dega dega) throws ParseException, IOException, NoSuchProviderException {
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("HH:mm - dd/MM/yyyy");
		
		String date= ft.format(dNow);
//		dega.getKrijimiDeges();
		dega.setModifikimiDeges(date);
		gjeneroPdf.gjeneroPdf(dega);
	    degaService.update(id);
	    return "redirect:/updateKey";
	}
}
