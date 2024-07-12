package com.Ecommer.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

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
import com.Ecommer.model.User;
import com.Ecommer.repositary.UserRepositary;

@Service
public class UserService {

    @Autowired
    private UserRepositary userRepositary;

    @Autowired
    private AuthencationService authenticationService;

    @Transactional
    public ResponseDto signup(signupdto signupDto) {
        // Check if a User with the given email already exists in the repository
        if (userRepositary.findByEmail(signupDto.getEmail()) != null) {
            // Throw a custom exception if the User already exists
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

        // Create a new User instance and set properties
        User newUser = new User();
        newUser.setFirstName(signupDto.getFirstName());
        newUser.setLastName(signupDto.getLastName());
        newUser.setEmail(signupDto.getEmail());
        newUser.setPassword(encryptedPassword); // Set the encrypted password

        // Save the new User
        userRepositary.save(newUser);

        // Create authentication token and save it
        Authencation authentication = new Authencation(newUser);
        authenticationService.saveConfirmationToken(authentication);

        // Create a response DTO indicating the signup was successful
        return new ResponseDto("success", "Signup successful");
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
        // Find the User by email
        User user = userRepositary.findByEmail(signInDto.getEmail());
        if (Objects.isNull(user)) {
            throw new AuthenticationFailException("User is not valid");
        }

        // Hash the provided password and compare
        try {
            if (!user.getPassword().equals(hashPassword(signInDto.getPassword()))) {
                throw new AuthenticationFailException("Wrong password");
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new CustomExecption("Error while hashing password: " + e.getMessage());
        }

        // Retrieve the authentication token
        Authencation token = authenticationService.getToken(user);
        if (Objects.isNull(token)) {
            throw new CustomExecption("Token is not present");
        }

        // Create a response DTO indicating successful sign-in
        return new SignInResponseDto("success", token.getToken());
    }
}
