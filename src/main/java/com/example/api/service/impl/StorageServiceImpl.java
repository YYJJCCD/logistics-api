package com.example.api.service.impl;

import com.example.api.model.entity.Employee;
import com.example.api.model.entity.Storage;
import com.example.api.repository.EmployeeRepository;
import com.example.api.repository.StorageRepository;
import com.example.api.service.StorageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class StorageServiceImpl implements StorageService {
    @Resource
    private StorageRepository storageRepository;

    @Resource
    private EmployeeRepository employeeRepository;

    @Override
    public List<Storage> findAll() {
        return storageRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        storageRepository.deleteById(id);
    }

    @Override
    public Storage save(Storage storage) {
        if(storage.getCreateAt() == null) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String createAt = df.format(System.currentTimeMillis());
            storage.setCreateAt(createAt);
        }
        return storageRepository.save(storage);
    }


    @Override
    public List<Employee> findAllEmployee() {
        return employeeRepository.findAll();
    }

}
