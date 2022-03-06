package com.example.api.service.impl;

import com.example.api.model.entity.Driver;
import com.example.api.repository.DriverRepository;
import com.example.api.service.DriverService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class DriverServiceImpl implements DriverService {
    @Resource
    private DriverRepository driverRepository;


    @Override
    public Driver save(Driver driver) {
        return driverRepository.save(driver);
    }

    @Override
    public void update(Driver driver) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strTime =  df.format(System.currentTimeMillis());
        driver.setUpdateAt(strTime);
        driverRepository.save(driver);
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Driver findById(String id) {
        return null;
    }

    @Override
    public List<Driver> findAll() {
        return null;
    }
}
