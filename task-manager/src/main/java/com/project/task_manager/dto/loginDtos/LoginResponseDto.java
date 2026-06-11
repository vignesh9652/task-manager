package com.project.task_manager.dto.loginDtos;

import com.project.task_manager.enums.Roles;
import lombok.Data;

@Data
public class LoginResponseDto {
    private Integer id;
    private String userName;
    private String email;
    private Roles role;
    private String token;
}
