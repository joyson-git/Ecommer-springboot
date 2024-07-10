package com.Ecommer.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Ecommer.dto.ResponseDto;
import com.Ecommer.dto.user.SignInDto;
import com.Ecommer.dto.user.SignInResponseDto;
import com.Ecommer.dto.user.signupdto;
import com.Ecommer.service.UserService;

@RequestMapping("user")
@RestController
public class Usercontroller {
	
	@Autowired
	UserService userService;
	// two apis
	
	//signup
	@PostMapping("/signup")
	public ResponseDto signup(@RequestBody signupdto signupdto) {
		return userService.signup(signupdto);
	}
	
	//signin
	
	@PostMapping("/signin")
    public SignInResponseDto signIn(@RequestBody SignInDto signInDto) {
        return userService.signIn(signInDto);
    }

}
