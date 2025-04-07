package com.quangsinh.cleanarchitecture.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import com.quangsinh.cleanarchitecture.domain.entity.User;
import com.quangsinh.cleanarchitecture.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService {
	private final UserRepository userRepository;
	private Long currentId = 0L;

	public UserServiceImplementation(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User createUser(String name, String email) {
		if (name == null || email == null) {
			throw new IllegalArgumentException("Name and email cannot be null");
		}
		User user = new User(++currentId, name, email);
		return userRepository.save(user);
	}

	@Override
	public User getUserById(Long id) {
		if (id == null) {
			throw new IllegalArgumentException("ID cannot be null");
		}
		return userRepository.findById(id);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User updateUser(Long id, String name, String email) {
		if (id == null) {
			throw new IllegalArgumentException("ID cannot be null");
		}
		User user = userRepository.findById(id);
		if (user == null) {
			throw new IllegalArgumentException("User not found");
		}
		user.setName(name);
		user.setEmail(email);
		return userRepository.save(user);
	}

	@Override
	public void deleteUser(Long id) {
		if (id == null) {
			throw new IllegalArgumentException("ID cannot be null");
		}
		userRepository.deleteById(id);
	}
}