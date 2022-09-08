package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

public class RegisterController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/register")
	public String Register() {
		return "/user/register";
	}
	
	@PostMapping("/register")
	public String PostHomeEmploye(@Validated User user, BindingResult bindingResult, Model model) {
		
		if(bindingResult.hasErrors()) {
			//System.out.println(bindingResult.getErrorCount());
			return "/user/register";
		}
		 userService.ajoutUSer(user);
		
		return "redirect:/";
	}
}
