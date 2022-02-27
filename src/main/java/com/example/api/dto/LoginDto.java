package com.example.api.dto;

import lombok.Data;

@Data
public class LoginDto {
    private String email;

    private String password;

    private String code;

    private boolean remember;
}
