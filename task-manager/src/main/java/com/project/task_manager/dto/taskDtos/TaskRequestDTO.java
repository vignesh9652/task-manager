package com.project.task_manager.dto.taskDtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskRequestDTO {
    private String title;
    private String description;
    private boolean status;
    private LocalDate dueDate;
}
