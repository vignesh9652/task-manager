package com.project.task_manager.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotBlank(message = "Title should not be empty")
    private String title;


    @NotBlank(message = "Description should not be empty")
    private String description;

    private boolean status;

    @NotNull(message = "Due date cannot be empty")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate  dueDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
