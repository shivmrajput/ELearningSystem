package com.app.pojos;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="course")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","student","syllabus"})
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "name",nullable=false)
	private String name;

	@Column(name = "description")
	private String description;
	
	@Column(name = "publish_date")
	private Date publishDate;
	
	@Column(name = "last_updated")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date lastUpdated;
	
	@Column(name = "total_hours")
	private int totalHours;
	
	@Column(name = "instructor")
	private String instructor;

	/*
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "student_id")
	@JsonIgnore
    private Student student;*/
	
	@ManyToMany(mappedBy = "courses",fetch = FetchType.LAZY)
    Set<Student> student=new HashSet<>();
	
	
	@OneToMany(mappedBy ="selectedCourse",orphanRemoval = true)
    List<Syllabus> syllabus = new ArrayList<>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public int getTotalHours() {
		return totalHours;
	}

	public void setTotalHours(int totalHours) {
		this.totalHours = totalHours;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public Set<Student> getStudent() {
		return student;
	}

	public void setStudent(Set<Student> student) {
		this.student = student;
	}
	
	
	//helper method for many to many
	public void addStudent(Student s) {
		student.add(s);
	}
	
    
	public List<Syllabus> getSyllabus() {
		return syllabus;
	}

	public void setSyllabus(List<Syllabus> syllabus) {
		this.syllabus = syllabus;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", description=" + description + ", publishDate=" + publishDate
				+ ", lastUpdated=" + lastUpdated + ", totalHours=" + totalHours + ", instructor=" + instructor + "]";
	}

	
	
	
}
