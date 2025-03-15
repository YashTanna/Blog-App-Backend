package com.yash.blog.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.yash.blog.entities.Category;
import com.yash.blog.entities.Post;
import com.yash.blog.entities.User;

public interface PostRepository extends JpaRepository<Post,Integer>{
	
	Page<Post> findByUser(User user,Pageable pageable);
	
	Page<Post> findByCategory(Category category,Pageable pageable);
	
	List<Post> findByTitleContaining(String keyword);
	
}
