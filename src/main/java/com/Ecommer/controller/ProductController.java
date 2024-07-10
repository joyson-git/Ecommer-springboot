package com.Ecommer.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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
import com.Ecommer.dto.ProductDto;
import com.Ecommer.model.Category;

import com.Ecommer.repositary.categoriesrepos;

import com.Ecommer.service.productservice;

@RestController
@RequestMapping("/product")
public class ProductController {

@Autowired
productservice Productservice ;
	
	
@Autowired
categoriesrepos  categoriesrepos;

@PostMapping("/add")
public ResponseEntity<APIResponse> createProduct(@RequestBody ProductDto productDto){
    Optional<Category> optionalCategory = categoriesrepos.findById(productDto.getId()); // Get the category by categoryId from productDto
    if (!optionalCategory.isPresent()) {  // Check for non-presence
        return new ResponseEntity<>(new APIResponse(false, "Category not found"), HttpStatus.BAD_REQUEST);
    }
    Productservice.createProduct(productDto, optionalCategory.get());
    return new ResponseEntity<>(new APIResponse(true, "Product created successfully"), HttpStatus.CREATED);
}

@GetMapping("/")
public ResponseEntity<List<ProductDto>> getProducts() {
    return Productservice.getAllProducts();
}


@PostMapping("/update/{productId}")
public ResponseEntity<APIResponse> updateProduct(
        @PathVariable("productId") Integer productId, 
        @RequestBody ProductDto productDto) {

    // Check if the category exists
    Optional<Category> optionalCategory = categoriesrepos.findById(productDto.getId());
    if (!optionalCategory.isPresent()) {
        return new ResponseEntity<>(new APIResponse(false, "Category not found"), HttpStatus.BAD_REQUEST);
    }

    // Check if the product exists and update it
    try {
        Productservice.updateProduct(productDto, productId);
        return new ResponseEntity<>(new APIResponse(true, "Product updated successfully"), HttpStatus.OK);
    } catch (NoSuchElementException e) {
        return new ResponseEntity<>(new APIResponse(false, "Product not found with ID: " + productId), HttpStatus.NOT_FOUND);
    } catch (Exception e) {
        // Catch any other exceptions and return an internal server error
        return new ResponseEntity<>(new APIResponse(false, "An error occurred: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


}
