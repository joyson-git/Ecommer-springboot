package com.Ecommer.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Ecommer.commer.APIResponse;
import com.Ecommer.model.Category;
import com.Ecommer.service.Categorieservice;




@RequestMapping("/category")
@RestController
public class categoriescontroller {

	@Autowired
	Categorieservice caterepostservice;
	
	@PostMapping("/create")
	public ResponseEntity<APIResponse> createCategoty(@RequestBody Category category) {
		caterepostservice.createcatgories(category);
		return new ResponseEntity<>(new APIResponse(true, "Category created successfully"), HttpStatus.CREATED);

	}
	
	 @GetMapping
	    public List<Category> listCategories() {
	        return caterepostservice.listCategories();
	    }
	 
	 @PostMapping("/update/{id}")
	 public ResponseEntity<APIResponse> updateCategory(@PathVariable int id, @RequestBody Category updateCategory) {
	        
	        		System.out.print(id);
	        
	        if (!caterepostservice.findById(id)) {
	        	caterepostservice.editCategory(id, updateCategory);
	            return new ResponseEntity<>(new APIResponse(true, "Category updated successfully"), HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(new APIResponse(false, "Category not found"), HttpStatus.NOT_FOUND);
	        }
	    }



}
