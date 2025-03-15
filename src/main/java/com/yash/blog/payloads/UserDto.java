package com.yash.blog.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor

@Setter
@Getter
public class UserDto {
	
	@NotNull
	private int id;
	
	
	@Size(min = 4,message = "Username must be minimum of 4 characters")
	private String name;
	
	@Email(message = "Email adderess is not valid !!")
	private String email;
	
	
	@Size(min = 3,max = 10,message = "Password must be min of 3 characters and max of 10 characters")
	private String password;
	
	@NotEmpty
	private String about;
}
