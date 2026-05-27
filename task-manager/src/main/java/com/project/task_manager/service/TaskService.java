package com.project.task_manager.service;

import com.project.task_manager.entity.Task;
import com.project.task_manager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task addTask(Task task){
       return taskRepository.save(task);
    }
}
