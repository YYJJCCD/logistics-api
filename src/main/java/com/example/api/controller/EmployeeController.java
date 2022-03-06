package com.example.api.controller;

import com.example.api.model.entity.Employee;
import com.example.api.service.EmployeeService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_EMPLOYEE')")
@RequestMapping("api/employee")
public class EmployeeController {
    @Resource
    private EmployeeService employeeService;

    @GetMapping("/findAll")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }


    @PostMapping("/save")
    public Employee save(@RequestBody Employee employee){
        return employeeService.save(employee);
    }

    @DeleteMapping("/deleteById")
    public void deleteById(String id){
        System.out.println(id);
        employeeService.deleteById(id);
    }

}
