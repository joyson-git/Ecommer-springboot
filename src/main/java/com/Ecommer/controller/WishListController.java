/*package com.Ecommer.controller;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Ecommer.commer.APIResponse;
import com.Ecommer.dto.ProductDto;
import com.Ecommer.model.WishList;
import com.Ecommer.model.product;
import com.Ecommer.model.User;
import com.Ecommer.service.AuthencationService;
import com.Ecommer.service.wishListservice;

@RestController
@RequestMapping("/wishlist")
public class WishListController {

	 @Autowired
	 wishListservice wishListService;

	    @Autowired
	    AuthencationService  authenticationService;

	    // Save product to wishlist
	    @PostMapping("/add")
	    public ResponseEntity<APIResponse> addToWishList(@RequestBody product product,
	                                                     @RequestParam("token") String token) {
	        // Authenticate the token
	        authenticationService.authenticate(token);

	        // Find the User
	        User User = authenticationService.getUser(token);

	        // Save the item in wishlist
	        WishList wishList = new WishList(User, product);
	        wishListService.createWishList(wishList);

	        APIResponse apiResponse = new APIResponse(true, "Added to wishlist");
	        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
	    }

	    // Get all wishlist items for a User
	    @GetMapping("/{token}")
	    public ResponseEntity<List<ProductDto>> getWishList(@PathVariable("token") String token) {
	        authenticationService.authenticate(token);

	        User User = authenticationService.getUser(token);
	        ResponseEntity<List<ProductDto>> responseEntity = wishListService.getWishListForUser(User);

	        return new ResponseEntity<>(responseEntity.getBody(), HttpStatus.OK);
	    }
	}*/