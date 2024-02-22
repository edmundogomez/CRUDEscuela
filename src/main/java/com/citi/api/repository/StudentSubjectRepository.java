package com.citi.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.citi.api.entity.StudentSubject;

public interface StudentSubjectRepository extends JpaRepository<StudentSubject,Integer>{
    
    //@Query("SELECT * FROM estudiante_materia AS em INNER JOIN materia AS m ON em.materia_id = m.id WHERE em.estudiante_id = ?1")
    //List<StudentSubject> selectListOfMateriaById(int student_id);
}
