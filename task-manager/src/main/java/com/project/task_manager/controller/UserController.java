package com.project.task_manager.controller;

import com.project.task_manager.dto.loginDtos.LoginRequestDto;
import com.project.task_manager.dto.loginDtos.LoginResponseDto;
import com.project.task_manager.dto.registerDtos.RegisterRequestDto;
import com.project.task_manager.dto.registerDtos.RegisterResponseDto;
import com.project.task_manager.response.ApiResponse;
import com.project.task_manager.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<RegisterResponseDto>> registerUser (@Valid @RequestBody RegisterRequestDto dto){
        RegisterResponseDto user = userService.registerUser(dto);
        ApiResponse<RegisterResponseDto> response = new ApiResponse<>("User Registered Successfully..",user);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponseDto>> loginUser(@Valid @RequestBody LoginRequestDto dto){
      LoginResponseDto user = userService.loginUser(dto);
      ApiResponse<LoginResponseDto> response = new ApiResponse<>("Login Successfully..",user);
      return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
