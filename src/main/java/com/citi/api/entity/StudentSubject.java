package com.citi.api.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "estudiante_materia")
public class StudentSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "estudiante_id")
    @Nonnull
    @JsonManagedReference
    private Student student;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "materia_id")
    @Nonnull
    @JsonManagedReference
    private Subject subject;

    @Column(name = "promedio")
    @Nonnull
    private int grade;

    public StudentSubject(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student studentId) {
        this.student = studentId;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subjectId) {
        this.subject = subjectId;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }


}
