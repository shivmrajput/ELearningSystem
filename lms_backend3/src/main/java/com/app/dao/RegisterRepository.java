package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.Student;

public interface RegisterRepository extends JpaRepository<Student, Long> {
	public Student findByUsername(String uname);
	public Student findByUsernameAndPassword(String uname,String pass);
}
