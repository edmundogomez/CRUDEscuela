package com.citi.api.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "estudiante")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;  

    @Column(name = "nombre")
    private String name;

    @Column(name = "apellidos")
    private String lastname;

    @Column(name = "promedio")
    private float grade;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
    @JsonIgnore
    @JsonManagedReference
    private List<StudentSubject> subjects;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public List<StudentSubject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<StudentSubject> subjects) {
        this.subjects = subjects;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String subname) {
        this.lastname = subname;
    }


}
