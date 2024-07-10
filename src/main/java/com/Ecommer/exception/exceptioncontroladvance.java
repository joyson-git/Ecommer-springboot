package com.Ecommer.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class exceptioncontroladvance {

	 @ExceptionHandler(value = CustomExecption.class) // Corrected annotation syntax
	    public final ResponseEntity<String> handleCustomException(CustomExecption exception) { // Fixed method signature
	        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
}
	 
	@ExceptionHandler(value = AuthenticationFailException.class) 
	 public final ResponseEntity<String> handleAuthenticationFailException(AuthenticationFailException exception){
	 return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	 }
}
