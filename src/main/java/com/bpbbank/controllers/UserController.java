package com.bpbbank.controllers;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.security.Principal;
import java.text.ParseException;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bpbbank.GjeneroAllPdf;
import com.bpbbank.dao.impl.KeyAuthenticationUserDaoImpl;
import com.bpbbank.domain.Dega;
import com.bpbbank.domain.KeyAuthenticationUser;
import com.bpbbank.service.DegaService;
import com.bpbbank.service.KeyAuthenticationUserService;



@Controller
public class UserController {

	private final Logger LOGGER = Logger.getLogger(UserController.class);
	
	@Autowired
	KeyAuthenticationUserService userService;
	@Autowired
	KeyAuthenticationUserDaoImpl userImpl;
	@Autowired
	GjeneroAllPdf gjeneroAllPdf = new GjeneroAllPdf();
	@Autowired
	DegaService degaService;
	
	private BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
	
	@GetMapping({"/users/add", "/users"})
	public String addUser(Model model, Principal principal) {
		LOGGER.info("Duke shfaqur formen per te shtuar user");
		model.addAttribute("user", new KeyAuthenticationUser());
		model.addAttribute("allUsers", userService.getAll());
		return "add_user";
	}
//	@RequestMapping(method=RequestMethod.POST, params="action=ruajPdf")
//	public String gjeneroAllPdf(Principal principal, Dega dega) throws FileNotFoundException, MalformedURLException, ParseException {
//		Set<Dega>deget = degaService.getAllDeget();
//		gjeneroAllPdf.gjeneroPdf(deget);
//	
//		return "add_user";
//	}
	
	@PostMapping("/users/add")
	public String addUser(@ModelAttribute KeyAuthenticationUser user, Model model, Principal principal) {
		LOGGER.info("Duke shtuar nje user");
		userService.initializeUser(user);
		model.addAttribute("user", new KeyAuthenticationUser());
		model.addAttribute("allUsers", userService.getAll());
		return "add_user";
	}
	
	@GetMapping("/users/password/reset/{username}")
	public String resetUserPassword(@PathVariable String username, Model model) {
		LOGGER.info("Duke resetuar passwordin per userin me username: " + username);
		userService.resetUserPassword(username);
		model.addAttribute("user", new KeyAuthenticationUser());
		model.addAttribute("allUsers", userService.getAll());
		return "add_user";
	}
	
	@GetMapping("/users/remove/{username}")
	public String removeUser(@PathVariable String username, Model model) {
		LOGGER.info("Duke fshire userin me username: " + username);
		userService.removeUser(username);
		model.addAttribute("user", new KeyAuthenticationUser());
		model.addAttribute("allUsers", userService.getAll());
		return "add_user";
	}
	
	@GetMapping("/users/change/status/{username}")
	public String changeUserStatus(@PathVariable String username, Model model) {
		LOGGER.info("Duke ndryshuar statusin e userit: " + username);
		userService.changeUserStatus(username);
		model.addAttribute("user", new KeyAuthenticationUser());
		model.addAttribute("allUsers", userService.getAll());
		return "add_user";
	}
	@GetMapping("/changePassword")
	public String changePassword(Model model, Principal principal) {
		LOGGER.info("Duke shfaqur formen per ndryshimin e passwordit");
		model.addAttribute("user", new KeyAuthenticationUser(principal.getName(),"",true));
		return "changePassword";
	}

	@RequestMapping(method = RequestMethod.POST, path="/home/changePassword")
	public String changePassword(@RequestParam("iRi")String password,@RequestParam("confirm") String confirmPassword, Model model, Principal principal) {
		LOGGER.info("Duke ndryshuar passwordin");
		if(userService.comparePassword(password, confirmPassword)) {
			KeyAuthenticationUser user = userService.getByUsername(principal.getName());
			LOGGER.info("Passwordi u nderrua me sukses");
			user.setPassword(encode.encode(password));
			user.setEnabled(true);
			userService.updateUser(user);
			return "redirect:/home";
		}
		return "changePassword";
	}
	
}

