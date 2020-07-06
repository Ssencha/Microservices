package com.eurekadiscovery.demo.user.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eurekadiscovery.demo.user.dto.CreateUserRequestDTO;
import com.eurekadiscovery.demo.user.dto.CreateUserResponseDTO;
import com.eurekadiscovery.demo.user.dto.UserDTO;
import com.eurekadiscovery.demo.user.service.UserService;

class TestObj {
	private List<String> list;
	private String name;
	public List<String> getList() {
		return list;
	}
	public void setList(List<String> list) {
		this.list = list;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private Environment env;

	@Autowired
	private UserService userService;

	@GetMapping("/userslist")
	public List<String> getUsers() {
		List<String> usersList = new ArrayList<>();
		usersList.add("Sunil");
		usersList.add("Sencha");
		return usersList;
	}

	@GetMapping("/status")
	public String getStatus() {
		return "Working on port: " + env.getProperty("local.server.port");
	}

	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, value = "/createuser")
	public ResponseEntity<CreateUserResponseDTO> createUser(@RequestBody CreateUserRequestDTO createUserRequestDTO) {
		UserDTO userDTO = new UserDTO();
		CreateUserResponseDTO createUserResponseDTO = new CreateUserResponseDTO();
		BeanUtils.copyProperties(createUserRequestDTO, userDTO);
		userService.createUser(userDTO);

		BeanUtils.copyProperties(userDTO, createUserResponseDTO);

		return ResponseEntity.status(HttpStatus.CREATED).body(createUserResponseDTO);

	}
	
	public static void main(String[] args) {
		TestObj obj = new TestObj();
		List<String> list = new ArrayList<>();
		list.add("Sunil");
		list.add("Sencha");
		obj.setList(list);
		obj.setName("TestObj");
		
		TestObj temp = new TestObj();
		
		BeanUtils.copyProperties(obj, temp);
//		List<String> list2 = new ArrayList<>();
//		temp.setList(list2);
		temp.getList().add("Anil");
		
		System.out.println(obj.getList().toString());
		System.out.println(temp.getList().toString());
	}

}
