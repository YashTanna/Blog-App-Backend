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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yash.blog.entities.Post;
import com.yash.blog.payloads.ApiResponce;
import com.yash.blog.payloads.PostDto;
import com.yash.blog.payloads.PostResponce;
import com.yash.blog.services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {

	@Autowired
	private PostService postService;
	
	//create Post
	
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,@PathVariable int userId,@PathVariable int categoryId){
		
		PostDto addedPost = postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(addedPost,HttpStatus.CREATED);
		
	}
	
	//Get post by user
	
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<PostResponce> getPostByUser(
			@PathVariable int userId,
			@RequestParam(name = "pageNumber",defaultValue = "0",required = false) int pageNumber,
			@RequestParam(name="pageSize",defaultValue = "5",required = false)int pageSize,
			@RequestParam(name = "sortBy", defaultValue = "postId",required = false) String sortBy,
			@RequestParam(name="sortDir",defaultValue = "asc",required = false) String sortDir
			){
		
		PostResponce posts = postService.getPostsByUser(userId,pageNumber,pageSize,sortBy,sortDir);
		
		return new ResponseEntity<PostResponce>(posts,HttpStatus.OK);
		
	}
	
	//get post by category
	
	@GetMapping("/category/{catId}/posts")
	public ResponseEntity<PostResponce> getPostByCategory(
			@PathVariable int catId,
			@RequestParam(name = "pageNumber",defaultValue = "0",required = false) int pageNumber,
			@RequestParam(name="pageSize",defaultValue = "5",required = false)int pageSize,
			@RequestParam(name = "sortBy", defaultValue = "postId",required = false) String sortBy,
			@RequestParam(name="sortDir",defaultValue = "asc",required = false) String sortDir
			){
		PostResponce posts = postService.getPostsByCategory(catId,pageNumber,pageSize,sortBy,sortDir);
		return new ResponseEntity<PostResponce>(posts,HttpStatus.OK);
	}
	
	//get post by id
	
	@GetMapping("posts/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable int postId){
		PostDto post = postService.getPostById(postId);
		return new ResponseEntity<PostDto>(post,HttpStatus.OK);
	}
	
	
	// update post
	@PutMapping("posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable int postId){
		PostDto updatedPost = postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatedPost,HttpStatus.OK);
	}
	
	// get all posts
	
	@GetMapping("posts")
	public ResponseEntity<PostResponce> getAllPost(
			@RequestParam(name = "pageNumber",defaultValue = "0",required = false) int pageNumber,
			@RequestParam(name="pageSize",defaultValue = "5",required = false)int pageSize,
			@RequestParam(name = "sortBy", defaultValue = "postId",required = false) String sortBy,
			@RequestParam(name="sortDir",defaultValue = "asc",required = false) String sortDir
			){
		
		PostResponce postRes = postService.getAllPost(pageNumber, pageSize,sortBy,sortDir);
		return new ResponseEntity<PostResponce>(postRes,HttpStatus.OK);
		
	}
	
	// delete post
	
	@DeleteMapping("posts/{postId}")
	public ResponseEntity<ApiResponce> deletePost(@PathVariable int postId){
		postService.deletePost(postId);
		return new ResponseEntity<ApiResponce>(new ApiResponce("Post is successfully deleted !!!",true),HttpStatus.OK);
	}
	
	// Search post by keyword
	@GetMapping("posts/search")
	public ResponseEntity<List<PostDto>> searchPost(@RequestParam(defaultValue = "",required = false) String keyword){
		List<PostDto> searchPosts = postService.searchPosts(keyword);
		return new ResponseEntity<List<PostDto>>(searchPosts,HttpStatus.OK);
	}
}
