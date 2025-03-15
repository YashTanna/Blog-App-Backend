package com.yash.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yash.blog.payloads.ApiResponce;
import com.yash.blog.payloads.UserDto;
import com.yash.blog.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto user){
		UserDto createdUser = userService.createUser(user);
		return new ResponseEntity<UserDto>(createdUser,HttpStatus.CREATED);	
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto,@PathVariable int userId){
		
		UserDto updatedUser =  userService.updateUser(userDto, userId);
		return ResponseEntity.ok(updatedUser);
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponce> deleteUser(@PathVariable int userId){
		userService.deleteUser(userId);
		return new ResponseEntity<ApiResponce>(new ApiResponce("User deleted successfully",true),HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUser(){
		return ResponseEntity.ok(userService.getAllUsers());
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUserById(@PathVariable int userId){
		return ResponseEntity.ok(userService.getUserById(userId));
	}
}











