package com.example.demo.dao;

import com.example.demo.entity.DepartmentEntity;
import com.example.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    List<UserEntity> findAllByDepartment(DepartmentEntity department);
    UserEntity findFirstByWorkId(String workId);
    List<UserEntity> findAllByDepartmentInAndUserNameLike(List<DepartmentEntity> departmentIds, String userName);
    @Query(value = "select * from user where user_name like ?1", nativeQuery = true)
    List<UserEntity> fuzzyQueryByName(String userName);
}
