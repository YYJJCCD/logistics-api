package com.example.api.service;


import com.example.api.model.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
    Employee save(Employee employee);
    void deleteById(String id);
}
