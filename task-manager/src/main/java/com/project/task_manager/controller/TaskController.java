package com.project.task_manager.controller;

import com.project.task_manager.entity.Task;
import com.project.task_manager.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public Task addTask(@Valid @RequestBody Task task) {
        return taskService.addTask(task);
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public Task getById(@PathVariable Integer id){
        return taskService.getTaskById(id)  ;
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id){
         taskService.deleteTaskById(id);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Integer id ,@Valid @RequestBody Task updatedTask ){
        return taskService.updateTask(id,updatedTask);
    }
}
