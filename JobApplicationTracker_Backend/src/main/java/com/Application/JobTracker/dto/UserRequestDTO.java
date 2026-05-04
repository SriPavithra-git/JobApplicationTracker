package com.Application.JobTracker.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UserRequestDTO {
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;
}
