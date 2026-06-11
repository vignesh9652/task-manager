package com.project.task_manager.dto.registerDtos;

import com.project.task_manager.enums.Roles;
import lombok.Data;


@Data
public class RegisterRequestDto {
    private String userName;
    private String email;
    private String password;
    private Roles role;
}
