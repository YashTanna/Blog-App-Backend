package com.yash.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.blog.entities.Category;
import com.yash.blog.entities.Post;
import com.yash.blog.entities.User;
import com.yash.blog.exceptions.ResourceNotFoundException;
import com.yash.blog.payloads.CategoryDto;
import com.yash.blog.payloads.PostDto;
import com.yash.blog.payloads.PostResponce;
import com.yash.blog.payloads.UserDto;
import com.yash.blog.repositories.PostRepository;
import com.yash.blog.services.CategoryService;
import com.yash.blog.services.PostService;
import com.yash.blog.services.UserService;

@Service
public class PostServiceImple implements PostService {

	@Autowired
	private PostRepository repo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Override
	public PostDto createPost(PostDto postDto,int userId,int categoryId) {
		
		UserDto userDto = userService.getUserById(userId);
		CategoryDto catDto = categoryService.getCategoryById(categoryId);
		
		Post post = modelMapper.map(postDto, Post.class);
		post.setAddedDate(new Date());
		post.setCategory(modelMapper.map(catDto, Category.class));
		post.setUser(modelMapper.map(userDto, User.class));
		post.setImageName("default.png");
		
		Post savedPost = repo.save(post);
		
		return modelMapper.map(savedPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, int postId) {

		Post post = repo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Posts", "Post Id", postId));
		
		post.setContent(postDto.getContent());
		post.setTitle(postDto.getTitle());
		
		Post updatedPost = repo.save(post);
		
		return modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePost(int postId) {
		
		Post post = repo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "PostId", postId));
		repo.delete(post);
	}

	@Override
	public PostDto getPostById(int postId) {
		
		Post post = repo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Posts", "Post Id", postId));
		
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostResponce getAllPost(int page,int pageSize,String sortBy,String sortDir) {

		Sort sort = (sortDir.equalsIgnoreCase("asc"))? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		
		Pageable pageRequest = PageRequest.of(page, pageSize,sort);
		Page<Post> pages = repo.findAll(pageRequest);
		
		List<Post> posts = pages.getContent();
		
		List<PostDto> postDto = posts.stream()
									.map((post)->modelMapper.map(post, PostDto.class))
									.collect(Collectors.toList());
		
		PostResponce postRes = new PostResponce();
		
		postRes.setContent(postDto);
		postRes.setPageNumber(pages.getNumber());
		postRes.setPageSize(pages.getSize());
		postRes.setTotalElement(pages.getTotalElements());
		postRes.setTotalPage(pages.getTotalPages());
		postRes.setLastPage(pages.isLast());
		
		return postRes;
	}

	@Override
	public PostResponce getPostsByCategory(int CategoryId,int page,int pageSize,String sortBy,String sortDir) {
		
		Sort sort = (sortDir.equalsIgnoreCase("asc"))? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		
		Pageable pageResquest = PageRequest.of(page, pageSize,sort);
		
		CategoryDto catDto = categoryService.getCategoryById(CategoryId);
		
		Page<Post> pages = repo.findByCategory(modelMapper.map(catDto, Category.class), pageResquest);

		List<Post> posts = pages.getContent();
		
		
		List<PostDto> postDtos = posts
									.stream()
									.map((post)->modelMapper.map(post, PostDto.class))
									.collect(Collectors.toList());
		
		PostResponce postRes = new PostResponce();
		
		postRes.setContent(postDtos);
		postRes.setPageNumber(pages.getNumber());
		postRes.setPageSize(pages.getSize());
		postRes.setTotalElement(pages.getTotalElements());
		postRes.setTotalPage(pages.getTotalPages());
		postRes.setLastPage(pages.isLast());
		
		return postRes;
	}

	@Override
	public PostResponce getPostsByUser(int userId,int page,int pageSize,String sortBy,String sortDir) {
		
		Sort sort = (sortDir.equalsIgnoreCase("asc"))? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		
		UserDto userDto = userService.getUserById(userId);
		
		Pageable pageReq = PageRequest.of(page, pageSize,sort);
		
		Page<Post> pages = repo.findByUser(modelMapper.map(userDto, User.class), pageReq);
		
		List<Post> posts = pages.getContent();
		
		List<PostDto> postDtos = posts
									.stream()
									.map((post)->modelMapper.map(post, PostDto.class))
									.collect(Collectors.toList());
		
		PostResponce postRes = new PostResponce();
		
		postRes.setContent(postDtos);
		postRes.setPageNumber(pages.getNumber());
		postRes.setPageSize(pages.getSize());
		postRes.setTotalElement(pages.getTotalElements());
		postRes.setTotalPage(pages.getTotalPages());
		postRes.setLastPage(pages.isLast());
		
		
		return postRes;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		
		List<Post> result = repo.findByTitleContaining(keyword);
		
		List<PostDto> serchPosts = result.stream().map((post)->modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		return serchPosts;
	}

}
