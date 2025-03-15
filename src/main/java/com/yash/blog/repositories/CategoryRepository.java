package com.yash.blog.repositories;

import com.yash.blog.entities.Category;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
