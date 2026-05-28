package com.project.task_manager.service;

import com.project.task_manager.dto.TaskRequestDTO;
import com.project.task_manager.dto.TaskResponseDTO;
import com.project.task_manager.entity.Task;
import com.project.task_manager.exception.TaskNotFoundException;
import com.project.task_manager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public TaskResponseDTO addTask(TaskRequestDTO dto){
        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setStatus(dto.isStatus());
        task.setDueDate(dto.getDueDate());

        Task savedTask = taskRepository.save(task);

        TaskResponseDTO taskResponseDTO = new TaskResponseDTO();

        taskResponseDTO.setId(savedTask.getId());
        taskResponseDTO.setTitle(savedTask.getTitle());
        taskResponseDTO.setDescription(savedTask.getDescription());
        taskResponseDTO.setStatus(savedTask.isStatus());

        return taskResponseDTO;
    }

   public List<TaskResponseDTO> getAllTasks(){
        List<Task> tasks = taskRepository.findAll();
        List<TaskResponseDTO> responseDTOList = new ArrayList<>();
        for (Task task : tasks){
            TaskResponseDTO taskResponseDTO = new TaskResponseDTO();
            taskResponseDTO.setId(task.getId());
            taskResponseDTO.setTitle(task.getTitle());
            taskResponseDTO.setDescription(task.getDescription());
            taskResponseDTO.setStatus(task.isStatus());
            responseDTOList.add(taskResponseDTO);
        }
        return responseDTOList;
   }

   //Get BY Id
    public TaskResponseDTO getTaskById(Integer id){
        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("Task not found"));
        TaskResponseDTO taskResponseDTO = new TaskResponseDTO();
        taskResponseDTO.setId(task.getId());
        taskResponseDTO.setTitle(task.getTitle());
        taskResponseDTO.setDescription(task.getDescription());
        taskResponseDTO.setStatus(task.isStatus());
        return taskResponseDTO;
    }
    //Delete BY Id
    public  void deleteTaskById(Integer id){
         taskRepository.deleteById(id);
    }
    //Editing
    public TaskResponseDTO updateTask(Integer id ,TaskRequestDTO dto){
        Task existingTask = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("task not found"));
        existingTask.setTitle(dto.getTitle());
        existingTask.setDescription(dto.getDescription());
        existingTask.setStatus(dto.isStatus());
        existingTask.setDueDate(dto.getDueDate());
         Task updatedTask = taskRepository.save(existingTask);
         TaskResponseDTO taskResponseDTO = new TaskResponseDTO();
         taskResponseDTO.setId(updatedTask.getId());
         taskResponseDTO.setTitle(updatedTask.getTitle());
         taskResponseDTO.setDescription(updatedTask.getDescription());
         taskResponseDTO.setStatus(updatedTask.isStatus());
         return taskResponseDTO;
    }
}
