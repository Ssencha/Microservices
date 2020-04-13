package com.eurekadiscovery.demo.account.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AcccountController {
	
	@GetMapping("/users")
	public List<String> getUsers() {
		List<String> usersList = new ArrayList<>();
		usersList.add("Sunil");
		usersList.add("Sencha");
		return usersList;
	}

}
