package com.app.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
	private Authentication auth;

//	@GetMapping("/")
//	public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
//			Model model) {
//		System.out.println("Home!");
//		auth = SecurityContextHolder.getContext().getAuthentication();
//		System.out.println(auth);
//		System.out.println(auth.getName());
//		model.addAttribute("name", name);
//		return "home";
//	}

	@RequestMapping("/CardTrading/home")
	public String authHome(Model model) {
		auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("Auth home!");
		System.out.println(auth);
		System.out.println(auth.getName());
		model.addAttribute("userName", auth.getName()).addAttribute("roles", auth.getAuthorities());
		return "auth-home";
	}
}