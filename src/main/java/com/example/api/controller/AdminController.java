package com.example.api.controller;

import com.example.api.dto.EmailDto;
import com.example.api.dto.LoginDto;
import com.example.api.model.entity.Admin;
import com.example.api.model.enums.Role;
import com.example.api.repository.AdminRepository;
import com.example.api.service.AdminService;
import com.example.api.utils.JwtUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Resource
    private AdminService adminService;

    @GetMapping("/basics/hasInit")
    public boolean hasInit() {
        return adminService.existsAdminByRoles(Role.ROLE_SUPER_ADMIN.getValue());
    }

    @PostMapping("/basics/init")
    public Admin init(@RequestBody Admin admin) throws Exception {
        if (adminService.existsAdminByRoles(Role.ROLE_SUPER_ADMIN.getValue())) throw new Exception("初始化错误");
        admin.setRoles(Role.ROLE_SUPER_ADMIN.getValue());
        admin.setCreateAt(String.valueOf(System.currentTimeMillis()));
        return adminService.save(admin);
    }

    @PostMapping("/basics/login")
    public Map<String, Object> login(String type, @RequestBody LoginDto dto) throws Exception {
        Admin admin = type.equals("email") ? adminService.loginByEmailAndCode(dto) : adminService.loginByEmailAndPassword(dto);
        String token = adminService.createToken(admin, dto.isRemember());
        Map<String, Object> map = new HashMap<>();
        map.put("admin", admin);
        map.put("token", token);
        return map;
    }

    @PostMapping("/basics/sendEmail")
    public void sendEmil(@RequestBody EmailDto dto) throws Exception{
        adminService.sendEmail(dto.getEmail());
    }
}