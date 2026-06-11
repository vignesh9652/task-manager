package com.project.task_manager.controller;

import com.project.task_manager.dto.taskDtos.TaskRequestDTO;
import com.project.task_manager.dto.taskDtos.TaskResponseDTO;
import com.project.task_manager.response.ApiResponse;
import com.project.task_manager.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<ApiResponse<TaskResponseDTO>> addTask(@Valid @RequestBody TaskRequestDTO dto) {
        TaskResponseDTO taskResponseDTO = taskService.addTask(dto);
        ApiResponse<TaskResponseDTO> response = new ApiResponse<>("Task Added Sucessfully...",taskResponseDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<TaskResponseDTO>>> getAllTasks() {
        List<TaskResponseDTO> tasks = taskService.getAllTasks();
        ApiResponse<List<TaskResponseDTO>> response = new ApiResponse<>("All Tasks...",tasks);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TaskResponseDTO>> getById(@PathVariable Integer id){
        TaskResponseDTO taskResponseDTO = taskService.getTaskById(id);
        ApiResponse<TaskResponseDTO> response = new ApiResponse<>("Task",taskResponseDTO);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteById(@PathVariable Integer id){
         taskService.deleteTaskById(id);
         ApiResponse<String> response = new ApiResponse<>("Task Deleted Successfully..",null);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TaskResponseDTO>> updateTask(@PathVariable Integer id ,@Valid @RequestBody TaskRequestDTO dto ){
        TaskResponseDTO taskResponseDTO = taskService.updateTask(id,dto);
        ApiResponse<TaskResponseDTO> response = new ApiResponse<>("Task Updated",taskResponseDTO);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/my-tasks")
    public ResponseEntity<ApiResponse<List<TaskResponseDTO>>> getMyTasks(){
        List<TaskResponseDTO> tasks = taskService.getMyTasks();
        ApiResponse<List<TaskResponseDTO>> response = new ApiResponse<>("Your Tasks",tasks);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
