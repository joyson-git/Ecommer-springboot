/*package com.Ecommer.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.service.internal.ProvidedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Ecommer.dto.ProductDto;
import com.Ecommer.model.WishList;
import com.Ecommer.model.User;
import com.Ecommer.repositary.wishlistrepositary;

@Service
public class wishListservice {
	
	 @Autowired
	   wishlistrepositary wishListRepository;

	    @Autowired
	    productservice productService;

	    public void createWishList(WishList wishList) {
	        wishListRepository.save(wishList);
	    }

	    public ResponseEntity<List<ProductDto>> getWishListForUser(User User) {
	        final List<WishList> wishLists = wishListRepository.findAllByUserOrderByCreatedDateDesc(User);
	        List<ProductDto> productDtos = new ArrayList<>();
	        for (WishList wishList : wishLists) {
	            productDtos.add(productService.getProductDto(wishList.getProduct()));
	        }
	        return ResponseEntity.ok(productDtos);
	    }
	}*/