package com.spring.restful.controlller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.restful.exceptions.StudentError;
import com.spring.restful.exceptions.StudentException;
import com.spring.restful.model.Student;

// http://localhost:8080/restful/
@RestController 
// http://localhost:8080/restful/main
@RequestMapping("main")
public class MainController {
	
	private List<Student> list = new ArrayList<>();
	
	@PostConstruct
	public void start() {
		list.add(new Student(1,"A",10));
		list.add(new Student(2,"B",20));
		list.add(new Student(3,"C",30));
		list.add(new Student(4,"D",40));
		list.add(new Student(5,"E",40));
	}
	
	// http://localhost:8080/restful/main/getMain
	@GetMapping("getMain")
	public String getMain() {
		return "main-page";
	}
	
	// http://localhost:8080/restful/main/students
	@GetMapping("students")
	public List<Student> getStudents(){
		return list;
	}
	
	
	// first way to get student by id using @PathVariable
	// http://localhost:8080/restful/main/student/id
	@GetMapping("student/{id}")
	public Student getStudentsById(@PathVariable("id") int id){
		if(id < 0 || id > list.size()) {
			throw new StudentException("Student with id :"+ id + " Not Found !" );
		}
		return list.get(id-1);
	}
	
	
	// second way to get student by id using @RequestParam
	// http://localhost:8080/restful/main/studentId?id=1
	@GetMapping("studentId")
	public Student getStudentId(@RequestParam int id){
		if(id < 0 || id > list.size()) {
			throw new StudentException("Student with id: "+ id + " Not Found !" );
		}
		return list.get(id-1);
	}
	
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
