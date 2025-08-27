  package com.example.taskmanagement.dto;

import com.example.taskmanagement.model.TaskStatus;
import java.time.Instant;

public class TaskResponse {
    private String id;
    private String title;
    private String description;
    private TaskStatus status;
    private Instant createdDate;
    private Instant updatedDate;

    // --- getters and setters ---
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }
    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Instant getUpdatedDate() {
        return updatedDate;
    }
    public void setUpdatedDate(Instant updatedDate) {
        this.updatedDate = updatedDate;
    }
}
