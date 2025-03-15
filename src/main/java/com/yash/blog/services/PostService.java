package com.yash.blog.services;

import java.util.List;
import com.yash.blog.payloads.PostDto;
import com.yash.blog.payloads.PostResponce;

public interface PostService {

	PostDto createPost(PostDto postDto,int userId,int categoryId);
	
	PostDto updatePost(PostDto postDto,int postId);
	
	void deletePost(int postId);
	
	PostDto getPostById(int postId);
	
	PostResponce getAllPost(int page,int pageSize,String sortBy,String sortDir);
	
	//	get all posts by category
	
	PostResponce getPostsByCategory(int CategoryId,int page,int pageSize,String sortBy,String sortDir);
	
	// get all posts by user
	
	PostResponce getPostsByUser(int userId,int page,int pageSize,String sortBy,String sortDir);
	
	// search posts
	List<PostDto> searchPosts(String keyword);
}
