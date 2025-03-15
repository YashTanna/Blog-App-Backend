package com.yash.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.blog.entities.Category;
import com.yash.blog.exceptions.ResourceNotFoundException;
import com.yash.blog.payloads.CategoryDto;
import com.yash.blog.repositories.CategoryRepository;
import com.yash.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepository repo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		
		Category cat = modelMapper.map(categoryDto, Category.class);
		Category addedCategory = repo.save(cat);

		return modelMapper.map(addedCategory, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, int categoryId) {
		
		Category cat = repo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "Category Id", categoryId));
		
		cat.setCategoryDescription(categoryDto.getCategoryDescription());
		cat.setCategoryName(categoryDto.getCategoryName());
		
		Category updatedCategory = repo.save(cat);
		
		return modelMapper.map(updatedCategory, CategoryDto.class);
	}

	@Override
	public void deleteCategory(int categoryId) {
		
		Category cat = repo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "Category Id", categoryId));
		repo.delete(cat);
	}

	@Override
	public CategoryDto getCategoryById(int categoryId) {

		Category cat = repo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "Category Id", categoryId));
		
		return modelMapper.map(cat, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		
		List<Category> cats = repo.findAll();
		
		List<CategoryDto> catsDto = cats.stream().map((cat) -> modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		
		return catsDto;
	}

}
