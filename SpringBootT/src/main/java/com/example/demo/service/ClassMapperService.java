package com.example.demo.service;

import com.example.demo.dao.ClassMapper;
import com.example.demo.entity.Class;
import com.example.demo.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassMapperService {
    @Autowired
    private ClassMapper classMapper;
    public void insertStudent(Student student){
        classMapper.insertStudent(student);
    }
    public void insertClass(Class sClass){
        classMapper.insertClass(sClass);
    }

    public void deleteStudentBySNo(String sno){
        classMapper.deleteStudentBySNo(sno);
    }
    public void updateStudent(Student student){
        classMapper.updateStudent(student);
    }

    public Student findStudentByName(String name){
        return classMapper.findStudentByName(name);
    }
    public List<Student> findAllStudent(){
        return classMapper.findAllStudent();
    }

    public Class findClassStudents(String name){
        return classMapper.findClassStudents(name);
    }
}
