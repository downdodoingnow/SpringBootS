package com.example.demo.dao;

import com.example.demo.entity.Class;
import com.example.demo.entity.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClassMapper {
    void insertStudent(Student student);
    void insertClass(Class sClass);

    void deleteStudentBySNo(String sno);
    void updateStudent(Student student);

    Student findStudentByName(String name);
    List<Student> findAllStudent();

    Class findClassStudents(String name);
}
