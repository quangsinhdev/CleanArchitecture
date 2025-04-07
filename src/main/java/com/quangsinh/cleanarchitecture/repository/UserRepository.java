package com.quangsinh.cleanarchitecture.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.quangsinh.cleanarchitecture.domain.entity.User;
@Repository
public interface UserRepository {
	User save(User user);

	User findById(Long id);
	
	void deleteById(Long id);
	
	List<User> findAll();
}