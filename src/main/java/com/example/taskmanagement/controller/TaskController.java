package com.example.taskmanagement.controller;

import com.example.taskmanagement.dto.TaskRequest;
import com.example.taskmanagement.dto.TaskResponse;
import com.example.taskmanagement.model.Task;
import com.example.taskmanagement.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
@Validated
public class TaskController {
    private final TaskService service;

    @Autowired
    public TaskController(TaskService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<TaskResponse> create(@Valid @RequestBody TaskRequest req) {
        Task created = service.createTask(req);
        return ResponseEntity.created(URI.create("/tasks/" + created.getId())).body(toResponse(created));
    }
    @GetMapping("/hello")
    public String hello() {
        return "Task Management API is running 🚀";
    }
    @GetMapping
    public ResponseEntity<?> getAll(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false, defaultValue = "createdDate") String sort) {

        if (page != null && size != null) {
            Pageable p = PageRequest.of(page, size, Sort.by(sort).descending());
            Page<Task> pg = service.getAllTasks(p);
            List<TaskResponse> content = pg.getContent().stream().map(this::toResponse).collect(Collectors.toList());
            return ResponseEntity.ok(content);
        } else {
            List<Task> all = service.getAllTasks();
            return ResponseEntity.ok(all.stream().map(this::toResponse).collect(Collectors.toList()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getById(@PathVariable String id) {
        return ResponseEntity.ok(toResponse(service.getTaskById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> update(@PathVariable String id, @Valid @RequestBody TaskRequest req) {
        return ResponseEntity.ok(toResponse(service.updateTask(id, req)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    private TaskResponse toResponse(Task t) {
        TaskResponse r = new TaskResponse();
        r.setId(t.getId());
        r.setTitle(t.getTitle());
        r.setDescription(t.getDescription());
        r.setStatus(t.getStatus());
        r.setCreatedDate(t.getCreatedDate());
        r.setUpdatedDate(t.getUpdatedDate());
        return r;
    }
}
