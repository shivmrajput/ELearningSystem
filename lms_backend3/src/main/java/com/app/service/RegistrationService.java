package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.RegisterRepository;
import com.app.pojos.Student;

@Service
public class RegistrationService  {
     @Autowired
     private RegisterRepository register;
     
     
	public Student saveStudent(Student newStudent) {
		return register.save(newStudent);
	}
	
	public Student fetchUserByUname(String uname) {
		return register.findByUsername(uname);
	}
	
	public Student fetchUserByUAndP(String uname,String pass) {
		return register.findByUsernameAndPassword(uname,pass);
	}

	
}
