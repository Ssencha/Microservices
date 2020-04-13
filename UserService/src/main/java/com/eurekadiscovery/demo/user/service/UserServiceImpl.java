package com.eurekadiscovery.demo.user.service;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.eurekadiscovery.demo.user.data.UserEntity;
import com.eurekadiscovery.demo.user.data.UserRepository;
import com.eurekadiscovery.demo.user.dto.UserDTO;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	private UserDetails userDetails;

	@Override
	public UserDTO createUser(UserDTO userDTO) {
		userDTO.setUserId(UUID.randomUUID().toString());
		userDTO.setEncryptedPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(userDTO, userEntity);
		userRepository.save(userEntity);

		return userDTO;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserEntity userEntity = userRepository.findByEmail(username);

		if (null == userEntity) {
			throw new UsernameNotFoundException(username);
		}

		UserDetails userDetails = new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), true, true, true,
				true, new ArrayList<>());
		return userDetails;
	}

	@Override
	public UserDTO getUserDetails(String username) {

		UserEntity userEntity = userRepository.findByEmail(username);

		if (null == userEntity) {
			throw new UsernameNotFoundException(username);
		}
		UserDTO userDTO = new UserDTO();
		BeanUtils.copyProperties(userEntity, userDTO);
		return userDTO;
	}

}
