package com.Ecommer.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import javax.security.sasl.AuthenticationException;

import org.apache.catalina.User;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Ecommer.dto.ResponseDto;
import com.Ecommer.dto.user.SignInDto;
import com.Ecommer.dto.user.SignInResponseDto;
import com.Ecommer.dto.user.signupdto;
import com.Ecommer.exception.AuthenticationFailException;
import com.Ecommer.exception.CustomExecption;
import com.Ecommer.model.Authencation;
import com.Ecommer.model.user;
import com.Ecommer.repositary.UserRepositary;




@Service
public class UserService {
	
@Autowired
UserRepositary userrepositary;




@Autowired
AuthencationService authencationService;
@Transactional
public ResponseDto signup(signupdto signupDto) {
    // Check if a user with the given email already exists in the repository
    if (userrepositary.findByEmail(signupDto.getEmail()) != null) {
        // Throw a custom exception if the user already exists
        throw new CustomExecption("User already present");
    }

    // Encrypt the password
    String encryptedPassword;
    try {
        encryptedPassword = hashPassword(signupDto.getPassword());
    } catch (NoSuchAlgorithmException e) {
        // If the hashing algorithm is not available, log the exception and throw a custom exception
        e.printStackTrace();
        throw new CustomExecption("Error while encrypting the password: " + e.getMessage());
    }

    
    user newUser = new user();
    newUser.setFirstName(signupDto.getFirstName());
    newUser.setLastName(signupDto.getLastName());
    newUser.setEmail(signupDto.getEmail());
    newUser.setPassword(encryptedPassword); // Set the encrypted password

    userrepositary.save(newUser);

    // Create a response DTO indicating the signup was successful
    
     final  Authencation  authentication  = new Authencation(newUser);
     authencationService.saveConfirmationToken(authentication);
    
    
    
     ResponseDto responseDto = new ResponseDto("success", "Signup successful");
     return responseDto;
}

// Method to hash the password using MD5
private String hashPassword(String password) throws NoSuchAlgorithmException {
    MessageDigest md = MessageDigest.getInstance("MD5");
    md.update(password.getBytes());
    byte[] digest = md.digest();
    // Convert the byte array into a hex string
    return bytesToHex(digest);
}

// Utility method to convert byte array to a hex string
private String bytesToHex(byte[] bytes) {
    StringBuilder sb = new StringBuilder();
    for (byte b : bytes) {
        sb.append(String.format("%02x", b));
    }
    return sb.toString().toUpperCase();
}



public SignInResponseDto signIn(SignInDto signInDto) {
	user user = userrepositary.findByEmail(signInDto.getEmail());
    if (Objects.isNull(user)) {
        throw new AuthenticationFailException("User is not valid");
    }

    // hash the password and compare it
    try {
        if (!user.getPassword().equals(hashPassword(signInDto.getPassword()))) {
            throw new AuthenticationFailException("Wrong password");
        }
    } catch (NoSuchAlgorithmException e) {
        e.printStackTrace();
        throw new CustomExecption("Error while hashing password: " + e.getMessage());
    }

    // retrieve the authentication token
    Authencation token = authencationService.getToken(user);
    if (Objects.isNull(token)) {
        throw new CustomExecption("Token is not present");
    }

    // Create a response DTO indicating successful sign-in
    return new SignInResponseDto("success", token.getToken());
}
}