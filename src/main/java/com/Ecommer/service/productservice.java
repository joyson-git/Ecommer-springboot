package com.Ecommer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Ecommer.dto.ProductDto;
import com.Ecommer.model.Category;
import com.Ecommer.model.product;
import com.Ecommer.repositary.productrepositary;

@Service
public class productservice {
@Autowired
productrepositary Productrepositary ;

public  void createProduct(ProductDto productDto, Category category) {
	product product = new product();
	product.setDescription(productDto.getDescription());
	product.setImageUrl(productDto.getImageUrl());
	product.setName(productDto.getName());
	product.setCategory(category);
	product.setPrice(productDto.getPrice());
	Productrepositary.save(product);
}

public  ProductDto getProductDto(product Product) {
	ProductDto productdto = new ProductDto();
	productdto.setDescription(Product.getDescription());
	productdto.setImageUrl(Product.getImageUrl());
	productdto.setName(Product.getName());
	productdto.setId(Product.getCategory().getId());
	productdto.setPrice(Product.getPrice());
	productdto.setId(Product.getId());
	return productdto;
}



public  ResponseEntity<List<ProductDto>> getAllProducts() {
    List<ProductDto> productDtos = new ArrayList<>();
    List<product> allProducts = Productrepositary.findAll();
    
    for (product product : allProducts) {
        productDtos.add(getProductDto(product));
    }
    
    return ResponseEntity.ok(productDtos);
}

public void updateProduct(ProductDto productDto, Integer productId) {
    Optional<product> optionalProduct = Productrepositary.findById(productId);

    // Throwing an exception if the product does not exist
    if (!optionalProduct.isPresent()) {
        throw new NoSuchElementException("Product not found with ID: " + productId);
    }

    // Update the existing product with data from the DTO
    product product = optionalProduct.get();
    product.setDescription(productDto.getDescription());
    product.setImageUrl(productDto.getImageUrl());
    product.setName(productDto.getName());
    product.setPrice(productDto.getPrice());

    // Save the updated product back to the repository
    Productrepositary.save(product);
}
}

