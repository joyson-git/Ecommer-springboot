package com.Ecommer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Ecommer.model.Authencation;
import com.Ecommer.model.user;
import com.Ecommer.repositary.Tokenrepositary;

@Service
public class AuthencationService {
	@Autowired
	Tokenrepositary tokenrepositary;

	public void saveConfirmationToken(Authencation authentication) {
		tokenrepositary.save(authentication);
		
	}

	public Authencation getToken(user user) {
		return tokenrepositary.findByUser(user);
	}

}
