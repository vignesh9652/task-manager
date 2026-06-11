package com.project.task_manager.entity;

import com.project.task_manager.enums.Roles;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "UserName should not be empty")
    private String userName;

    @NotBlank(message = "Email not to be empty")
    private String email;

    @NotBlank(message = "Must Fill the password")
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Roles role;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<Task> tasks;
}
