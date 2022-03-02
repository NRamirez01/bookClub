package com.nathanielramirez.bookclub.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nathanielramirez.bookclub.models.User;
import com.nathanielramirez.bookclub.repositories.UserRepository;

@Service
public class UserService {
    
	@Autowired
	private UserRepository userRepository;
	
	
//	Login
	public Optional<User> userByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	public Optional<User> userByID(Long id) {
		return userRepository.findById(id);
	}
	
	public User create(User user) {
		return this.userRepository.save(user);
	}
	
}
