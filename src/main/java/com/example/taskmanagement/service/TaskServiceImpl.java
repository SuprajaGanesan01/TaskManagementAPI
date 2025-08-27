package com.example.taskmanagement.service;

import com.example.taskmanagement.dto.TaskRequest;
import com.example.taskmanagement.exception.ResourceNotFoundException;
import com.example.taskmanagement.model.Task;
import com.example.taskmanagement.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository repo;

    @Autowired
    public TaskServiceImpl(TaskRepository repo) { this.repo = repo; }

    @Override
    public Task createTask(TaskRequest request) {
        Task t = new Task(request.getTitle(), request.getDescription(), request.getStatus());
        return repo.save(t);
    }

    @Override
    public List<Task> getAllTasks() { return repo.findAll(); }

    @Override
    public Page<Task> getAllTasks(Pageable pageable) { return repo.findAll(pageable); }

    @Override
    public Task getTaskById(String id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found: " + id));
    }

    @Override
    public Task updateTask(String id, TaskRequest request) {
        Task t = getTaskById(id);
        t.setTitle(request.getTitle());
        t.setDescription(request.getDescription());
        t.setStatus(request.getStatus());
        return repo.save(t);
    }

    @Override
    public void deleteTask(String id) {
        Task t = getTaskById(id);
        repo.delete(t);
    }
}
