package com.example.demo.dao;

import com.example.demo.entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepartmentRepository extends JpaRepository<DepartmentEntity, Long> {
}
