package com.project.task_manager.dto;

import lombok.Data;

@Data
public class TaskResponseDTO {
    private Integer id;
    private  String title;
    private  String description;
    private boolean status;
}
