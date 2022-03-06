package com.example.api.model.dto;

import lombok.Data;

@Data
public class LoginDto {
    private String email;

    private String password;

    private String code;

    private boolean remember;
}
