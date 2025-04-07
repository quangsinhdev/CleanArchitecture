package com.quangsinh.cleanarchitecture.usecase;

import java.util.List;

import com.quangsinh.cleanarchitecture.domain.entity.User;

public interface UserService {
	User createUser(String name, String email);

	User getUserById(Long id);

	List<User> getAllUsers();

	User updateUser(Long id, String name, String email);

	void deleteUser(Long id);
}