package com.Application.JobTracker.dto;

import com.Application.JobTracker.entity.JobStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDate;

@Data
public class JobRequestDTO {
    private String companyName;
    private String role;

    @Enumerated(EnumType.STRING)
    private JobStatus jobStatus;

    private LocalDate appliedDate;
    private LocalDate interviewDate;

    @Column(length = 1000)
    private String notes;

}
