package com.Application.JobTracker.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserResponseDTO {
    private List<JobResponseDTO> jobs;
}
