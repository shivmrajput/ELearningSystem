package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.CourseRepository;
import com.app.dao.SyllabusRepository;
import com.app.exception.ResourceNotFoundException;
import com.app.pojos.Course;
import com.app.pojos.Syllabus;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class SyllabusController {
	
	 @Autowired
	 private SyllabusRepository syllabusRepository;
	 
	 @Autowired
	 private CourseRepository courseRepository;
	
	
	 @GetMapping("/syllabus/{id}")
	 public ResponseEntity<List<Syllabus>> getAllCourse(@PathVariable long id){
		 Course course=courseRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("course with given id not found"));
		 return ResponseEntity.ok(course.getSyllabus());
		
	 }
	 
	 

	 @PostMapping("/syllabus/{id}")
	    public ResponseEntity<Syllabus> createSyllabus(@PathVariable long id , @RequestBody Syllabus syllabus) {
	       
		 Course course=courseRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("course with given id not found"));
	     syllabus.setSelectedCourse(course);
	     Syllabus s=syllabusRepository.save(syllabus);
	     return ResponseEntity.ok(s);
		 
	 }
	         

}
