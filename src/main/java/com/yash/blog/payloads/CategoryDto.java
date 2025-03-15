package com.yash.blog.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

	private int categoryId;
	
	@NotBlank
	@Size(min = 3,message = "Category name must be minimum of 3 characters")
	private String categoryName;
	
	@NotBlank
	@Size(min = 10,message = "Categoey description must be minimum of 10 characters")
	private String categoryDescription;
	
}
