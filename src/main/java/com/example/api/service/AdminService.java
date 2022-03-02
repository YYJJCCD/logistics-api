package com.example.api.service;

import com.example.api.dto.LoginDto;
import com.example.api.model.entity.Admin;

import java.util.List;


public interface AdminService {
    Admin save(Admin admin) throws Exception;

    Admin findById(String id);

    Admin loginByEmailAndPassword(LoginDto dto) throws Exception;

    Admin loginByEmailAndCode(LoginDto dto) throws Exception;

    List<Admin> findAll();

    String createToken(Admin admin, boolean isRemember);

    void deleteByUid(String uid);

    void sendEmail(String email) throws Exception;

    boolean existsAdminByEmail(String email);

    boolean existsAdminByRoles(String roles);
}
