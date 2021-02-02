package com.app.controller;



import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.CourseRepository;
import com.app.dao.RegisterRepository;
import com.app.exception.ResourceNotFoundException;
import com.app.pojos.Course;
import com.app.pojos.MappingTable;
import com.app.pojos.Student;



@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class StudentController {
	
	@Autowired
	private RegisterRepository studentRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@PutMapping("/student_course")
	 public ResponseEntity<String> studentSelectedCourse(@RequestBody MappingTable obj){
		String result="Updation failed";
		long student_id=obj.getStudent_id();
		long course_id=obj.getCourse_id();
		
		Course oldcourse=courseRepository.findById(course_id).orElseThrow(()->new ResourceNotFoundException("course with given id not found"));
		Student oldstudent=studentRepository.findById(student_id).orElseThrow(()->new ResourceNotFoundException("student with given id not found"));
		
		oldstudent.addCourses(oldcourse);
		courseRepository.save(oldcourse);
		studentRepository.save(oldstudent);
		result="Updation sucessful";
		 return ResponseEntity.ok(result);	 
	 }
	
	
	@GetMapping("/student_course/{id}")
	 public ResponseEntity<Set<Course>> getStudentSelectedCourse(@PathVariable long id){
		
		Student student=studentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("student with given id not found"));
		return ResponseEntity.ok(student.getCourses());
 
	 }

}
