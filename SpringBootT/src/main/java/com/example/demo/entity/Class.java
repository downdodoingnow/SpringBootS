package com.example.demo.entity;

import java.util.List;

public class Class {
    private String classId;
    private String name;
    private List<Student> students;

    public Class(String classId, String name, List<Student> students) {
        this.classId = classId;
        this.name = name;
        this.students = students;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
