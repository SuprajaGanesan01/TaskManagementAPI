package com.example.taskmanagement.service;

import com.example.taskmanagement.dto.TaskRequest;
import com.example.taskmanagement.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TaskService {
    Task createTask(TaskRequest request);
    List<Task> getAllTasks();
    Page<Task> getAllTasks(Pageable pageable);
    Task getTaskById(String id);
    Task updateTask(String id, TaskRequest request);
    void deleteTask(String id);
}
