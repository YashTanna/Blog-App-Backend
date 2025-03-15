package com.yash.blog.services.impl;

import java.util.List;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.blog.entities.User;
import com.yash.blog.exceptions.ResourceNotFoundException;
import com.yash.blog.payloads.UserDto;
import com.yash.blog.repositories.UserRepository;
import com.yash.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.dtoToUser(userDto);
		User savedUser = userRepo.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, int userId) {
		
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId)); 
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		userRepo.save(user);
		return userToDto(user);
	}

	@Override
	public UserDto getUserById(int userId) {

		User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "Id", userId));
		return userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {

		List<User> users = userRepo.findAll();
		
		List<UserDto> userDtos =  users.stream().map(user -> userToDto(user)).collect(Collectors.toList());
		
		return userDtos;
	}

	
	
	User dtoToUser(UserDto userDto) {
		return modelMapper.map(userDto, User.class);
	}
	
	UserDto userToDto(User user) {
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public void deleteUser(int userId) {
		User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "Id", userId));
		userRepo.delete(user);
	}

}
