package com.project.task_manager.dto.registerDtos;

import com.project.task_manager.enums.Roles;
import lombok.Data;

@Data
public class RegisterResponseDto {
    private Integer id;
    private  String userName;
    private String email;
    private Roles role;
}
