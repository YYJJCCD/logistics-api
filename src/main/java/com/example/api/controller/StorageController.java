package com.example.api.controller;

import com.example.api.model.entity.Employee;
import com.example.api.model.entity.Storage;
import com.example.api.service.StorageService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_STORAGE')")
@RequestMapping("/api/storage")
public class StorageController {
    @Resource
    private StorageService storageService;

    @GetMapping("/findAll")
    public List<Storage> findAll() {
        return storageService.findAll();
    }

    @GetMapping("/findAllEmployee")
    public List<Employee> findAllEmployee(){
        return storageService.findAllEmployee();
    }

    @PostMapping("/save")
    public Storage save(@RequestBody Storage storage) {
        return storageService.save(storage);
    }

    @DeleteMapping("/deleteById")
    public void deleteById(String id) {
        storageService.deleteById(id);
    }

}
