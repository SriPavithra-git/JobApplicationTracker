package com.Application.JobTracker.dto;

import com.Application.JobTracker.entity.JobStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class JobRequestDTO {

    @NotBlank(message = "Company name is required")
    private String companyName;

    @NotBlank(message = "Role is required")
    private String role;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Status is required")
    private JobStatus jobStatus;

    @NotNull(message = "Applied date is required")
    private LocalDate appliedDate;
    private LocalDate interviewDate;

    @Column(length = 1000)
    private String notes;

}
