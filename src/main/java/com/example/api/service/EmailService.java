package com.example.api.service;

import org.springframework.mail.MailException;

public interface EmailService {
    void sendVerificationCode(String email) throws MailException;

    boolean checkVerificationCode(String email, String code);
}
