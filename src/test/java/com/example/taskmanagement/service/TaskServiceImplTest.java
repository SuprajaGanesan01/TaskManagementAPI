package com.example.taskmanagement.service;

import com.example.taskmanagement.dto.TaskRequest;
import com.example.taskmanagement.model.Task;
import com.example.taskmanagement.model.TaskStatus;
import com.example.taskmanagement.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTask() {
        TaskRequest request = new TaskRequest();
        request.setTitle("Learn Spring Boot");
        request.setDescription("Practice CRUD APIs");
        request.setStatus(TaskStatus.PENDING);

        Task savedTask = new Task("Learn Spring Boot", "Practice CRUD APIs", TaskStatus.PENDING);
        savedTask.setId("1");  // ✅ use String instead of long

        when(taskRepository.save(any(Task.class))).thenReturn(savedTask);

        Task result = taskService.createTask(request);

        assertEquals("Learn Spring Boot", result.getTitle());
        verify(taskRepository, times(1)).save(any(Task.class));
    }

    @Test
    void testGetTaskById() {
        Task task = new Task("Read Docs", "JUnit5", TaskStatus.IN_PROGRESS);
        task.setId("2");  // ✅ String ID

        when(taskRepository.findById("2")).thenReturn(Optional.of(task)); // ✅ String ID

        Task result = taskService.getTaskById("2"); // ✅ String ID

        assertEquals("Read Docs", result.getTitle());
        assertEquals(TaskStatus.IN_PROGRESS, result.getStatus());
    }
}
