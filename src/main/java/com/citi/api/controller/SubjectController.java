package com.citi.api.controller;

import java.util.List;

import org.hibernate.boot.jaxb.hbm.spi.SubEntityInfo;
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

import com.citi.api.entity.Subject;
import com.citi.api.service.SubjectService;

@RestController
@RequestMapping("/api")
public class SubjectController {

    @Autowired
    private SubjectService service;

    @GetMapping("/materias")
    public List<Subject> findAllSubjects(){
        return service.getSubjects();
    }

    @GetMapping("/materias/{materiaId}")
    public Subject getSubject(@PathVariable int materiaId){
        return service.getSubjectById(materiaId);
    }

    @PostMapping(value = "/materias", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Subject createSubject(@RequestBody Subject subject){
        return service.saveSubject(subject);
    }

    @PutMapping(value = "/materias/{materiaId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Subject updateSubject(@RequestBody Subject subject){
        return service.updateSubject(subject);
    }

    @DeleteMapping(value = "/materias")
    public void deleteSubject(@RequestBody Subject subject){
        service.deleteSubject(subject);
    }
}
