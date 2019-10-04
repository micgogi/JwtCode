package com.test.controller;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.test.model.ResponseMessage;



public class ExceptionHandlingController {
	Logger logger = LoggerFactory.getLogger(ExceptionHandlingController.class);
	String defaultMessage = "";

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseMessage> handleError(Exception ex) {
		logger.info("In handler" + ex.getMessage());
		if (ex instanceof MethodArgumentNotValidException) {
			defaultMessage = "Error:\n ";
			MethodArgumentNotValidException exp = (MethodArgumentNotValidException) ex;
			BindingResult bindingResult = exp.getBindingResult();
			List<FieldError> fieldError = bindingResult.getFieldErrors();
			fieldError.stream().forEach(err -> {
				String test = err.getDefaultMessage() + ",\n";
				defaultMessage += test;
			});
			return new ResponseEntity<ResponseMessage>(
	    			  new ResponseMessage(defaultMessage.substring(0, defaultMessage.length() - 1),400),HttpStatus.BAD_REQUEST);
		}
      if(ex instanceof BadCredentialsException){
			defaultMessage="error:";
			BadCredentialsException exc=(BadCredentialsException)ex;
			return new ResponseEntity<ResponseMessage>(
	    			  new ResponseMessage(exc.getMessage(),406),HttpStatus.BAD_REQUEST);
		}
      if(ex instanceof ConstraintViolationException){
    	  ConstraintViolationException constraintException=(ConstraintViolationException)ex;
    	  Set<ConstraintViolation<?>> set=constraintException.getConstraintViolations();
    	  String errorMessage="Input Validation failed:";
    	  for(ConstraintViolation<?> constraintViolation:set)
    		  errorMessage+=constraintViolation.getMessageTemplate()+",";
    	  return new ResponseEntity<ResponseMessage>(
    			  new ResponseMessage(errorMessage.substring(0,errorMessage.length()-1),400),HttpStatus.BAD_REQUEST);
      }
      if (ex instanceof InternalAuthenticationServiceException) {
			InternalAuthenticationServiceException iase = (InternalAuthenticationServiceException) ex;
			return new ResponseEntity<ResponseMessage>(new ResponseMessage(iase.getMessage(), 409), HttpStatus.CONFLICT);
      }
      if (ex instanceof HttpMediaTypeNotSupportedException) {
			HttpMediaTypeNotSupportedException he = (HttpMediaTypeNotSupportedException) ex;
			return new ResponseEntity<ResponseMessage>(new ResponseMessage(he.getMessage(), 415),
					HttpStatus.UNSUPPORTED_MEDIA_TYPE);
      }
     
		return new ResponseEntity<ResponseMessage>(new ResponseMessage("Error:"+ex.getMessage(),500),HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
