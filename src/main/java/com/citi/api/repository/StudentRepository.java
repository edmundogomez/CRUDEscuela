package com.citi.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.citi.api.entity.Student;

public interface StudentRepository extends JpaRepository<Student,Integer>{

    //@Query(value = "SELECT ")
    //void getAllSubjects(); 
}
