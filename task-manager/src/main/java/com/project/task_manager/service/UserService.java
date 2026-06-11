package com.project.task_manager.service;

import com.project.task_manager.dto.loginDtos.LoginRequestDto;
import com.project.task_manager.dto.loginDtos.LoginResponseDto;
import com.project.task_manager.dto.registerDtos.RegisterRequestDto;
import com.project.task_manager.dto.registerDtos.RegisterResponseDto;
import com.project.task_manager.entity.User;
import com.project.task_manager.exception.EmailAlreadyExistsException;
import com.project.task_manager.exception.InvalidCredentialsException;
import com.project.task_manager.jwt.JwtService;
import com.project.task_manager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JwtService jwtService;

    public RegisterResponseDto registerUser(RegisterRequestDto dto){
       Optional<User> existingUser = userRepository.findUserByEmail(dto.getEmail());
       if(existingUser.isPresent()){
           throw new EmailAlreadyExistsException("Email already exists..");
       }
       User user = new User();
       user.setUserName(dto.getUserName());
       user.setEmail(dto.getEmail());
       user.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
       user.setRole(dto.getRole());

       User savedUser = userRepository.save(user);

       RegisterResponseDto registerResponseDto = new RegisterResponseDto();
        registerResponseDto.setId(savedUser.getId());
        registerResponseDto.setUserName(savedUser.getUserName());
        registerResponseDto.setEmail(savedUser.getEmail());
        registerResponseDto.setRole(savedUser.getRole());

        return registerResponseDto;
    }
    public LoginResponseDto loginUser(LoginRequestDto dto){
     Optional<User> user = userRepository.findUserByEmail(dto.getEmail());
     if (user.isEmpty()){
         throw new InvalidCredentialsException("Invalid Credentials..");
     }
       boolean isPasswordMatched = bCryptPasswordEncoder .matches(dto.getPassword(), user.get().getPassword());
      if (!isPasswordMatched){
          throw  new InvalidCredentialsException("Invalid credentials..");
      }
      String token = jwtService.generateToken(user.get().getEmail());
        LoginResponseDto loginResponseDto = new LoginResponseDto();
        loginResponseDto.setId(user.get().getId());
        loginResponseDto.setUserName(user.get().getUserName());
        loginResponseDto.setEmail(user.get().getEmail());
        loginResponseDto.setRole(user.get().getRole());
        loginResponseDto.setToken(token);
        return loginResponseDto;
    }
}
