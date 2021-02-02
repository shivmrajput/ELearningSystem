package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojos.Student;
import com.app.service.RegistrationService;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class RegistrationController {
    
	@Autowired
	private RegistrationService service;
	
	
	
	@PostMapping("/register")
	public Student registerStudent(@RequestBody Student newStudent)throws Exception {
		String username=newStudent.getUsername();
		if(username !=null && !"".equals(username)) {
			Student s=service.fetchUserByUname(username);
			if(s!=null) {
				throw new Exception("user with "+username+" already exists");
			}
		}
		Student newstu=service.saveStudent(newStudent);
		return newstu;
	}
	
	@PostMapping("/login")
	public Student loginStudent(@RequestBody Student student)throws Exception{
		String username=student.getUsername();
		String pass=student.getPassword();
		Student s=null;
		if(username!=null && pass!=null) {
			 s=service.fetchUserByUAndP(username, pass);
		}
		if(s==null) {
			throw new Exception("user doesn't  exists");
		}
		return s;
	}
	
	  
}
