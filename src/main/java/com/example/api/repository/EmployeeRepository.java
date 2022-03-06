package com.example.api.repository;

import com.example.api.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
    void deleteById(String id);
}
