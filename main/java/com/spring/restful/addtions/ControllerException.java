package com.spring.restful.addtions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.spring.restful.exceptions.StudentError;
import com.spring.restful.exceptions.StudentException;

@ControllerAdvice
public class ControllerException {
	
	@ExceptionHandler
	public ResponseEntity<StudentError> getStudentException(StudentException seException){
		StudentError studentError = new StudentError();
		studentError.setStutsCode(HttpStatus.NOT_FOUND.value());
		studentError.setMessage(seException.getMessage());
		studentError.setTimeStamp(System.currentTimeMillis());
		return new ResponseEntity<StudentError>(studentError,HttpStatus.NOT_FOUND);				
	}
	
	@ExceptionHandler
	public ResponseEntity<StudentError> getAnyException(Exception seException){
		StudentError studentError = new StudentError();
		studentError.setStutsCode(HttpStatus.BAD_REQUEST.value());
		studentError.setMessage(seException.getMessage());
		studentError.setTimeStamp(System.currentTimeMillis());
		return new ResponseEntity<StudentError>(studentError,HttpStatus.BAD_REQUEST);				
	}


}
