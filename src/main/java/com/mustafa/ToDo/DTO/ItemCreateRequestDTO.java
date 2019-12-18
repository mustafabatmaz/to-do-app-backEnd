package com.mustafa.ToDo.DTO;

import com.mustafa.ToDo.Entity.Item;
import com.mustafa.ToDo.Enum.Status;

import java.time.LocalDateTime;

public class ItemCreateRequestDTO {
    private String name;
    private String description;
    private int deadline;
    private Status status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDeadline() {
        return deadline;
    }

    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
