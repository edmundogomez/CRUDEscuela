package com.citi.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citi.api.entity.Student;
import com.citi.api.entity.StudentSubject;
import com.citi.api.entity.Subject;
import com.citi.api.repository.StudentRepository;
import com.citi.api.repository.StudentSubjectRepository;
import com.citi.api.repository.SubjectRepository;

@Service
public class StudentService {
    @Autowired
    private StudentRepository repository;

    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private StudentSubjectRepository studentSubjectRepository;

    public List<Student> getStudents(){
        return repository.findAll();
    }

    public Student getStudentById(int id){
        return repository.findById(id).orElse(null);
    }

    public Student saveStudent(Student student){
        return repository.save(student);
    }

    public Student updateStudent(Student student){
        Student existingStudent = repository.findById(student.getId()).orElse(null);

        existingStudent.setName(student.getName());
        existingStudent.setLastname(student.getLastname());
        existingStudent.setGrade(student.getGrade());

        return repository.save(existingStudent);
    }

    public void deleteStudent(Student student){
        repository.deleteById(student.getId());
    }

    public StudentSubject addSubjectToStudent(int studentId,int subjectId,int grade){
        Student student = repository.findById(studentId).orElse(null);
        Subject subject = subjectRepository.findById(subjectId).orElse(null);

        StudentSubject aux = new StudentSubject();

        if(student == null || subject == null || grade < 0 || grade > 100)
            return aux;

        aux.setStudent(student);
        aux.setSubject(subject);
        aux.setGrade(grade);

        return studentSubjectRepository.save(aux);
    }

}
