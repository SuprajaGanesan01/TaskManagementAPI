package com.example.taskmanagement.repository;

import com.example.taskmanagement.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskRepository extends MongoRepository<Task, String> {}
