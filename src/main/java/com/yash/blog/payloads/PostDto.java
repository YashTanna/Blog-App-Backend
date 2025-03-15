package com.yash.blog.payloads;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {
	
	private int postId;

	private String title;
	
	private String content;
	
	private String imageName;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
	private Date addedDate;
	
	private CategoryDto category;
	
	private UserDto user;
	
}
