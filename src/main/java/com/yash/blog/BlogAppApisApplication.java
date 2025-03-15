package com.yash.blog;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class BlogAppApisApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogAppApisApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Applies to all endpoints
                        .allowedOrigins("*") // Accepts requests from any domain
                        .allowedMethods("*") // Allows all HTTP methods (GET, POST, PUT, DELETE, etc.)
                        .allowedHeaders("*"); // Accepts all headers
            }
        };
    }

}
