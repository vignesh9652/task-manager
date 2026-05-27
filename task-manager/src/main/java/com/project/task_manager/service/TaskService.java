package com.project.task_manager.service;

import com.project.task_manager.entity.Task;
import com.project.task_manager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task addTask(Task task){
       return taskRepository.save(task);
    }

   public List<Task> getAllTasks(){
        return taskRepository.findAll();
   }

   //Get BY Id
    public Task getTaskById(Integer id){
        return taskRepository.getById(id);
    }
    //Delete BY Id
    public  void deleteTaskById(Integer id){
         taskRepository.deleteById(id);
    }
    //Editing
    public Task updateTask(Integer id ,Task updatedTask){
        Task existingTask = taskRepository.getById(id);
        existingTask.setTitle(updatedTask.getTitle());
        existingTask.setDescription(updatedTask.getDescription());
        existingTask.setStatus(updatedTask.isStatus());
        existingTask.setDueDate(updatedTask.getDueDate());
        return taskRepository.save(existingTask);
    }
}
