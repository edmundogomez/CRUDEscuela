package com.citi.api.DTO;

public class StudentSubjectDTO {
    private int studentId; 
    private int subjectId;
    private int grade;

    public int getStudentId() {
        return studentId;
    }
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
    public int getSubjectId() {
        return subjectId;
    }
    public void setSubjectId(int subjetcId) {
        this.subjectId = subjetcId;
    }
    public int getGrade() {
        return grade;
    }
    public void setGrade(int grade) {
        this.grade = grade;
    }

}
