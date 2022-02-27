package com.example.api.service.impl;

import com.example.api.model.entity.Code;
import com.example.api.repository.CodeRepository;
import com.example.api.service.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Random;

@Service
public class EmailServiceImpl implements EmailService {
    @Resource
    private JavaMailSender mailSender;

    @Resource
    private CodeRepository codeRepository;

    @Value("${spring.mail.username}")
    private String emailSender;


    /**
     * 根据邮箱发送6位数字验证码
     * @param email 用户邮箱
     * @throws MailException 发送失败
     */
    @Override
    public void sendVerificationCode(String email) throws MailException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailSender);
        message.setTo(email);
        message.setSubject("验证码");

        Random random = new Random();
        StringBuilder codeBuilder = new StringBuilder();
        for(int i = 0; i < 6; i++){
            codeBuilder.append(random.nextInt(10));
        }
        String codeValue = codeBuilder.toString();
        message.setText("您的验证码为: " + codeValue + " 15 分钟内有效");
        mailSender.send(message);
        codeRepository.save(new Code(email, codeValue));
    }

    /**
     * 检查验证码的正确性以及时效性
     * @param email 用户邮箱
     * @param value 用户提供的验证码
     * @return 验证码是否匹配
     */
    @Override
    public boolean checkVerificationCode(String email, String value) {
        Code code = codeRepository.findCodeByEmailAndValue(email, value);
        long nowTime = System.currentTimeMillis();
        if(code == null || nowTime > code.getExpiration()) return false;
        codeRepository.delete(code);
        return true;
    }

}
