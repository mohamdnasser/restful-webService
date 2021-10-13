package com.spring.restful.controlller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.restful.exceptions.StudentException;
import com.spring.restful.model.Student;

// http://localhost:8080/restful/
@RestController 
// http://localhost:8080/restful/main
@RequestMapping("main2")
public class MainController2 {
	
	private List<Student> list = new ArrayList<>();
	
	@PostConstruct
	public void start() {
		list.add(new Student(1,"A",10));
		list.add(new Student(2,"B",20));
		list.add(new Student(3,"C",30));
		list.add(new Student(4,"D",40));
		list.add(new Student(5,"E",40));
	}
	

	// second way to get student by id using @RequestParam
	// http://localhost:8080/restful/main2/studentId?id=1
	@GetMapping("studentId")
	public Student getStudentId(@RequestParam int id){
		if(id < 0 || id > list.size()) {
			throw new StudentException("Student with id: "+ id + " Not Found !" );
		}
		return list.get(id-1);
	}
	


}
