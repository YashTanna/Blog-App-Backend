package com.yash.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.yash.blog.payloads.CategoryDto;
import com.yash.blog.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService service;
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
		CategoryDto createdDto = service.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(createdDto,HttpStatus.CREATED);
	}
	
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto,@PathVariable int categoryId){
		CategoryDto updatedDto = service.updateCategory(categoryDto, categoryId);
		return new ResponseEntity<CategoryDto>(updatedDto,HttpStatus.OK);
	}
	
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponce> deleteCategory(@PathVariable int categoryId){
		service.deleteCategory(categoryId);
		return new ResponseEntity<ApiResponce>(new ApiResponce("Category is deleted Successfully",true),HttpStatus.OK);
	}
	
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable int categoryId){
		CategoryDto newCatrgory =  service.getCategoryById(categoryId);
		return new ResponseEntity<CategoryDto>(newCatrgory,HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategory(){
		List<CategoryDto> cat = service.getAllCategory();
		return new ResponseEntity<List<CategoryDto>>(cat,HttpStatus.OK);
	}
	
}




