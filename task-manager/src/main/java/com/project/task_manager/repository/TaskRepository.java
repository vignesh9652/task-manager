package com.project.task_manager.repository;

import com.project.task_manager.entity.Task;
import com.project.task_manager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Integer> {
    List<Task> findByUser(User user);
}

