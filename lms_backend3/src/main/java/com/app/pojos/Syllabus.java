package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="syllabus")
//@JsonIgnoreProperties({"hibernateLazyInitializer","handler","selectedCourse"})
public class Syllabus {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "description_details")
	private String description_details ;
	
	@ManyToOne
	@JoinColumn(name = "c_id",nullable = false)
	private Course selectedCourse;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription_details() {
		return description_details;
	}

	public void setDescription_details(String description_details) {
		this.description_details = description_details;
	}

	public Course getSelectedCourse() {
		return selectedCourse;
	}

	public void setSelectedCourse(Course selectedCourse) {
		this.selectedCourse = selectedCourse;
	}
    
	
	
	
	
	
	

}
