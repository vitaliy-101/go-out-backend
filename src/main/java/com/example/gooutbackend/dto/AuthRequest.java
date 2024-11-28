package com.example.gooutbackend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {
    private String nickname;
    private String password;
}
