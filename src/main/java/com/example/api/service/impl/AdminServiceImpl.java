package com.example.api.service.impl;

import com.example.api.dto.LoginDto;
import com.example.api.model.entity.Admin;
import com.example.api.repository.AdminRepository;
import com.example.api.repository.CodeRepository;
import com.example.api.service.AdminService;
import com.example.api.service.EmailService;
import com.example.api.utils.CheckUtil;
import com.example.api.utils.JwtUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminRepository adminRepository;

    @Resource
    private EmailService emailService;

    /**
     * 提交管理员信息
     *
     * @param admin 管理员对象
     * @return 管理员信息
     * @throws Exception 邮箱格式错误或密码格式错误
     */
    @Override
    public Admin save(Admin admin) throws Exception {
        String email = admin.getEmail();
        String password = admin.getPassword();
        if(existsAdminByEmail(email)) throw new Exception("邮箱已存在");
        if (!CheckUtil.checkEmail(email)) throw new Exception("邮箱格式错误");
        if (!CheckUtil.checkPassword(password)) throw new Exception("密码格式错误");
        admin.setCreateAt(String.valueOf(System.currentTimeMillis()));
        return adminRepository.save(admin);
    }

    @Override
    public Admin findById(String id) {
        return adminRepository.findById(id).orElse(null);
    }

    @Override
    public Admin loginByEmailAndPassword(LoginDto dto) throws Exception{
        String email = dto.getEmail();
        String password = dto.getPassword();
        Admin admin = adminRepository.findAdminByEmailAndPassword(email, password);
        if(admin == null) throw new Exception("用户名或密码错误");
        return admin;
    }

    @Override
    public Admin loginByEmailAndCode(LoginDto dto) throws Exception{
        if(emailService.checkVerificationCode(dto.getEmail(), dto.getCode())){
            return adminRepository.findAdminByEmail(dto.getEmail());
        }
        throw new Exception("验证码过期或错误");
    }

    @Override
    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    @Override
    public String createToken(Admin admin, boolean isRemember) {
        String rolesString = admin.getRoles();
        String[] roles = rolesString != null? rolesString.split(";") : null;
        return JwtUtil.createToken(admin.getEmail(), roles, isRemember);
    }

    @Override
    public void deleteByUid(String uid) {
        adminRepository.deleteByUid(uid);
    }

    /**
     * 验证码邮件发送
     * @param email 用户邮箱
     */
    @Override
    public void sendEmail(String email) throws Exception{
        Admin admin = adminRepository.findAdminByEmail(email);
        if (admin == null) throw new Exception("账户不存在");
        emailService.sendVerificationCode(email);
    }

    @Override
    public boolean existsAdminByEmail(String email) {
        return adminRepository.existsAdminByEmail(email);
    }

    @Override
    public boolean existsAdminByRoles(String roles) {
        return adminRepository.existsAdminByRoles(roles);
    }
}
