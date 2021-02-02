package com.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.CourseRepository;
import com.app.dao.RegisterRepository;
import com.app.exception.ResourceNotFoundException;
import com.app.pojos.Course;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class AdminController {
	
	@Autowired
	private CourseRepository CRepository;
	
	@Autowired
	private RegisterRepository studentRepository;
	
	
	 @GetMapping("/courses")
	 public List<Course> getAllCourse(){
		 return CRepository.findAll();
	 }
	 
	 //create course
	 @PostMapping("/courses")
	 public Course createCourse(@RequestBody Course c){
		 return CRepository.save(c);
	 }
	 
	 @GetMapping("/courses/{id}")
	 public ResponseEntity<Course> getCourseById(@PathVariable long id) {
		 Course course = CRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("course with given id not found"));
		 return ResponseEntity.ok(course);
	 }
	 
	 @PutMapping("/courses/{id}")
	 public ResponseEntity<Course> updateCourse(@PathVariable long id,@RequestBody Course c){
		 Course course = CRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("course with given id not found"));
		 course.setDescription(c.getDescription());
		 course.setInstructor(c.getInstructor());
		 course.setLastUpdated(c.getLastUpdated());
		 course.setName(c.getName());
		 course.setPublishDate(c.getPublishDate());
		 course.setTotalHours(c.getTotalHours());
		 Course updatedcourse=CRepository.save(course);
		 return ResponseEntity.ok(updatedcourse);
	 }
	 
	 
	 //delete the course
	 @DeleteMapping("/courses/{id}")
	 public ResponseEntity<Map<String,Boolean>> deleteCourse(@PathVariable long id){
		 Course course = CRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("course with given id not found"));
		 //group.getUsers().removeAll(group.getUsers());
		    /*Group group = groupRepository.findById(groupId).orElseThrow();
		    group.getUsers().forEach(u -> u.getGroups().remove(group));
		    userRepository.saveAll(group.getUsers());
		    groupRepository.delete(group);*/
		 
		 course.getStudent().forEach(u->u.getCourses().remove(course));
		 studentRepository.saveAll(course.getStudent());
		 CRepository.delete(course);
		 Map<String,Boolean> res = new HashMap<>();
		 res.put("Deleted sucessfully", Boolean.TRUE);
		 return ResponseEntity.ok(res);
	 }
	 

}
