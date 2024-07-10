package com.Ecommer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Ecommer.model.Category;
import com.Ecommer.repositary.categoriesrepos;

@Service 
public class Categorieservice {

	@Autowired
	  private categoriesrepos  categoriesrepos;
	
	public void  createcatgories(Category categories) {
		categoriesrepos.save(categories);
		
	}
	
	
	 public List<Category> listCategories() {
	        return categoriesrepos.findAll();
	    }
	
	
	 public void editCategory(int category,Category updatecategories){
		 Category categories = categoriesrepos.getById(category);
		 categories .setCategoryname(updatecategories.getCategoryname());
		 categories .setDescription(updatecategories.getDescription());
		 categories .setImageUrl(updatecategories.getImageUrl());
		 categoriesrepos.save(categories);
	 }


	 public boolean findById(int id) {
			
			return  categoriesrepos.findById(id).isPresent();
		}
	 
	 
}
