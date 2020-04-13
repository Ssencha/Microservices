package com.eurekadiscovery.demo.user.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.eurekadiscovery.demo.user.dto.UserDTO;

public interface UserService extends UserDetailsService{
	public UserDTO createUser(UserDTO userDTO);
	public UserDTO getUserDetailsByEmail(String email);
	public UserDTO getUserDetailsById(String userId);
}
