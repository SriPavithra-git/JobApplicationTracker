package com.Application.JobTracker.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name= "jobs")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long job_id;

    private String companyName;
    private String role;

    @Enumerated(EnumType.STRING)
    private JobStatus jobStatus;

    private LocalDate appliedDate;
    private LocalDate interviewDate;

    @Column(length = 1000)
    private String notes;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
