package com.example.demo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.dao.RepartmentRepository;
import com.example.demo.dao.UserRepository;
import com.example.demo.entity.DepartmentEntity;
import com.example.demo.entity.Student;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.ClassMapperService;
import com.example.demo.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class TestController {
    @Autowired
    private ClassMapperService service;
    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    UserRepository userRepository;
    @Autowired
    RepartmentRepository departmentRepository;

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

    @GetMapping("test_redis")
    public String test_redis(){
        System.out.println("test_redis");
        redisUtils.set("name","dengzi");
        return (String) redisUtils.get("name");
    }

    @GetMapping("test_jpa")
    public String test_Jpa(){
        DepartmentEntity deptEntity1 = new DepartmentEntity();
        deptEntity1.setDeptName("研发部门1");
        deptEntity1.setId(3L);
        DepartmentEntity deptEntity2 = new DepartmentEntity();
        deptEntity2.setDeptName("产品部门1");
        deptEntity2.setId(4L);
        // 写入部门信息
        departmentRepository.save(deptEntity1);
        departmentRepository.save(deptEntity2);
        departmentRepository.flush();

        UserEntity entity1 = new UserEntity();
        entity1.setWorkId("1234567");
        entity1.setDepartment(deptEntity1);
        entity1.setUserName("王小二1");
        UserEntity entity2 = new UserEntity();
        entity2.setWorkId("2345678");
        entity2.setDepartment(deptEntity1);
        entity2.setUserName("王小五1");
        UserEntity entity3 = new UserEntity();
        entity3.setWorkId("3456789");
        entity3.setDepartment(deptEntity1);
        entity3.setUserName("刘大壮1");
        UserEntity entity4 = new UserEntity();
        entity4.setWorkId("3456789");
        entity4.setDepartment(deptEntity2);
        entity4.setUserName("张三1");
        // 写入用户信息
        userRepository.saveAll(Stream.of(entity1, entity2, entity3, entity4).collect(Collectors.toList()));
        userRepository.flush();
        return "okk";
    }
}
