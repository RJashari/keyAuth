package com.bpbbank.controllers;

import java.security.Principal;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.bpbbank.domain.KeyAuthenticationUser;
import com.bpbbank.service.KeyAuthenticationUserService;

@Controller
public class UserController {

	private final Logger LOGGER = Logger.getLogger(UserController.class);
	
	@Autowired
	KeyAuthenticationUserService userService;
	
	@GetMapping({"/users/add", "/users"})
	public String addUser(Model model, Principal principal) {
		model.addAttribute("user", new KeyAuthenticationUser());
		model.addAttribute("allUsers", userService.getAll());
		return "add_user";
	}
	
	@PostMapping("/users/add")
	public String addUser(@ModelAttribute KeyAuthenticationUser user, Model model, Principal principal) {
		userService.initializeUser(user);
		model.addAttribute("user", new KeyAuthenticationUser());
		model.addAttribute("allUsers", userService.getAll());
		return "add_user";
	}
	
	@GetMapping("/users/password/reset/{username}")
	public String resetUserPassword(@PathVariable String username, Model model) {
		userService.resetUserPassword(username);
		model.addAttribute("user", new KeyAuthenticationUser());
		model.addAttribute("allUsers", userService.getAll());
		return "add_user";
	}
	
	@GetMapping("/users/remove/{username}")
	public String removeUser(@PathVariable String username, Model model) {
		userService.removeUser(username);
		model.addAttribute("user", new KeyAuthenticationUser());
		model.addAttribute("allUsers", userService.getAll());
		return "add_user";
	}
	
	@GetMapping("/users/change/status/{username}")
	public String changeUserStatus(@PathVariable String username, Model model) {
		LOGGER.info("Changing status of the user: " + username);
		userService.changeUserStatus(username);
		model.addAttribute("user", new KeyAuthenticationUser());
		model.addAttribute("allUsers", userService.getAll());
		return "add_user";
	}
}
