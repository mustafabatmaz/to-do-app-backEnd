package com.mustafa.ToDo.DTO;

import com.mustafa.ToDo.Enum.Status;

public class ItemUpdateRequestDTO {
    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
