package com.citi.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citi.api.DTO.StudentSubjectDTO;
import com.citi.api.entity.Student;
import com.citi.api.entity.StudentSubject;
import com.citi.api.service.StudentService;

@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    private StudentService service;

    @GetMapping("/estudiantes")
    public List<Student> findAllStudents(){
        return service.getStudents();
    }

    @GetMapping("/estudiantes/{estudianteId}")
    public Student getStudent(@PathVariable int estudianteId){
        return service.getStudentById(estudianteId);
    }

    @PostMapping(value = "/estudiantes", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Student createStudent(@RequestBody Student student){
        return service.saveStudent(student);
    }

    @PutMapping(value = "/estudiantes/{estudianteId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Student updateStudent(@RequestBody Student student){
        return service.updateStudent(student);
    }

    @DeleteMapping(value = "/estudiantes")
    public void deleteStudent(@RequestBody Student student){
        service.deleteStudent(student);
    }

    @GetMapping("/estudiantes/{estudianteId}/materia")
    public StudentSubject getStudentMateria(@PathVariable int estudianteId){
        return new StudentSubject();
    }

    @PostMapping(value = "/estudiantes/{estudianteId}/materia", consumes = MediaType.APPLICATION_JSON_VALUE)
    public StudentSubject createStudentMateria(@PathVariable int estudianteId, @RequestBody StudentSubjectDTO dto){
        return service.addSubjectToStudent(estudianteId,dto.getSubjectId(),dto.getGrade());
    }
}
