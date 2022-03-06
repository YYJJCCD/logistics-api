package com.example.api.service;

import com.example.api.model.entity.Employee;
import com.example.api.model.entity.Storage;

import java.util.List;

public interface StorageService {
    List<Storage> findAll();

    void deleteById(String id);

    Storage save(Storage storage);

    List<Employee> findAllEmployee();
}
