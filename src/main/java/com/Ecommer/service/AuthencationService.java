package com.Ecommer.service;

import java.util.Objects;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Ecommer.exception.AuthenticationFailException;
import com.Ecommer.model.Authencation;
import com.Ecommer.model.User;
import com.Ecommer.repositary.Tokenrepositary;

@Service
public class AuthencationService {
	@Autowired
	Tokenrepositary tokenrepositary;

	public void saveConfirmationToken(Authencation authentication) {
		tokenrepositary.save(authentication);
		
	}

	public Authencation getToken(User User) {
		return tokenrepositary.findByUser(User);
	}
	
	public User getUser(String token) {
        final Authencation authenticationToken = tokenrepositary.findByToken(token);
        if (Objects.isNull(authenticationToken)) {
            return null;
        }
        return authenticationToken.getUser();
    }

    public void authenticate(String token) throws AuthenticationFailException {
        if (Objects.isNull(token)) {
            throw new AuthenticationFailException("Token not present");
        }
        if (Objects.isNull(getUser(token))) {
            throw new AuthenticationFailException("Token not valid");
        }
    }
}