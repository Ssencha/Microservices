package com.eurekadiscovery.demo.user.data;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

	public UserEntity findByEmail(String userName);
	public UserEntity findByUserId(String userId);
}
