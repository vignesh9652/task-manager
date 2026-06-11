package com.project.task_manager.service;

import com.project.task_manager.dto.taskDtos.TaskRequestDTO;
import com.project.task_manager.dto.taskDtos.TaskResponseDTO;
import com.project.task_manager.entity.Task;
import com.project.task_manager.entity.User;
import com.project.task_manager.exception.TaskNotFoundException;
import com.project.task_manager.exception.UnauthorizedException;
import com.project.task_manager.exception.UserNotFoundException;
import com.project.task_manager.repository.TaskRepository;

import com.project.task_manager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    public TaskResponseDTO addTask(TaskRequestDTO dto){
        String email = getCurrentUserEmail();
        User user = userRepository.findUserByEmail(email).orElseThrow(() -> new UserNotFoundException("User not found.."));
        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setStatus(dto.isStatus());
        task.setDueDate(dto.getDueDate());

        task.setUser(user);

        Task savedTask = taskRepository.save(task);

        TaskResponseDTO taskResponseDTO = new TaskResponseDTO();

        taskResponseDTO.setId(savedTask.getId());
        taskResponseDTO.setTitle(savedTask.getTitle());
        taskResponseDTO.setDescription(savedTask.getDescription());
        taskResponseDTO.setStatus(savedTask.isStatus());
        taskResponseDTO.setDueDate(savedTask.getDueDate());

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
            taskResponseDTO.setDueDate(task.getDueDate());
            responseDTOList.add(taskResponseDTO);
        }
        return responseDTOList;
   }

   //Get BY Id
    public TaskResponseDTO getTaskById(Integer id){

        String email = getCurrentUserEmail();
        User user = userRepository.findUserByEmail(email).orElseThrow(() -> new UserNotFoundException("User not found.."));

        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("Task not found"));

        if(!task.getUser().getId().equals(user.getId())){
            throw  new UnauthorizedException("You are not authorized to get task");
        }
        TaskResponseDTO taskResponseDTO = new TaskResponseDTO();
        taskResponseDTO.setId(task.getId());
        taskResponseDTO.setTitle(task.getTitle());
        taskResponseDTO.setDescription(task.getDescription());
        taskResponseDTO.setStatus(task.isStatus());
        taskResponseDTO.setDueDate(task.getDueDate());

        return taskResponseDTO;
    }
    //Delete BY Id
    public  void deleteTaskById(Integer id){

        String email = getCurrentUserEmail();
        User user = userRepository.findUserByEmail(email).orElseThrow(() -> new UserNotFoundException("User not found.."));

        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("Task not found.."));

        if (!task.getUser().getId().equals(user.getId())){
            throw new UnauthorizedException("Your not authorized to delete this task..");
        }
        taskRepository.deleteById(id);
    }
    //Editing
    public TaskResponseDTO updateTask(Integer id ,TaskRequestDTO dto){
        String email = getCurrentUserEmail();
        User user = userRepository.findUserByEmail(email).orElseThrow(() -> new UserNotFoundException("User not found.."));

        Task existingTask = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("task not found"));

        if(!existingTask.getUser().getId().equals(user.getId())){
            throw  new UnauthorizedException("You are not authorized to update the task..");
        }
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
         taskResponseDTO.setDueDate(updatedTask.getDueDate());
         return taskResponseDTO;
    }

    public String getCurrentUserEmail(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = null;
        if(authentication != null){
            user = (User) authentication.getPrincipal();
        }
        return  user.getEmail();
    }

    public List<TaskResponseDTO> getMyTasks(){
        String email = getCurrentUserEmail();
        User user = userRepository.findUserByEmail(email).orElseThrow(()  -> new UserNotFoundException("User not found.."));

        List<Task> tasks = taskRepository.findByUser(user);

        //creation of list through ArrayList
        List<TaskResponseDTO> taskResponseDTOList = new ArrayList<>();

        for(Task task : tasks){
            TaskResponseDTO dto = new TaskResponseDTO();
            dto.setId(task.getId());
            dto.setTitle(task.getTitle());
            dto.setDescription(task.getDescription());
            dto.setStatus(task.isStatus());
            dto.setDueDate(task.getDueDate());
            taskResponseDTOList.add(dto);
        }
        return taskResponseDTOList;
    }
}
