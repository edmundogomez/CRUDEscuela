package com.citi.api.service;

import java.util.List;

import org.hibernate.boot.jaxb.hbm.spi.SubEntityInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citi.api.entity.Subject;
import com.citi.api.repository.SubjectRepository;

@Service
public class SubjectService {
    @Autowired
    private SubjectRepository repository;

    public List<Subject> getSubjects(){
        return repository.findAll();
    }

    public Subject getSubjectById(int id){
        return repository.findById(id).orElse(null);
    }

    public Subject saveSubject(Subject subject){
        return repository.save(subject);
    }

    public Subject updateSubject(Subject subject){
        Subject existingSubject = repository.findById(subject.getId()).orElse(null);

        existingSubject.setName(subject.getName());

        return repository.save(existingSubject);
    }

    public void deleteSubject(Subject subject){
        repository.deleteById(subject.getId());
    }
}
