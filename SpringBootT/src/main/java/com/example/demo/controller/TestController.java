package com.example.demo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.Student;
import com.example.demo.service.ClassMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {
    @Autowired
    private ClassMapperService service;
    @GetMapping("/test")
    public String test(){
        return "Hello world";
    }

    @GetMapping("get_all_students")
    public String getAllStudents(){
        System.out.println("getAllStudents");
        List<Student> studentList = service.findAllStudent();
        JSONArray jsonArray = new JSONArray();
        for(Student student:studentList){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("sno",student.getSno());
            jsonObject.put("classId",student.getClassId());
            jsonObject.put("name",student.getName());
            jsonObject.put("grade",student.getGrade());
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }
}
