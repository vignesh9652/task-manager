package com.project.task_manager.dto.loginDtos;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String email;
    private String password;
}
