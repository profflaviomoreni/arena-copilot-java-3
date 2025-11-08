package br.fiap.arena.api.dto;

import jakarta.validation.constraints.NotNull;

public class TaskStatusRequest {

    @NotNull(message = "Status is required")
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
