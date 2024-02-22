package com.citi.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.citi.api.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject,Integer>{
    
}
