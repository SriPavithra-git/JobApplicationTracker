package com.Application.JobTracker.service;

import com.Application.JobTracker.dto.JobRequestDTO;
import com.Application.JobTracker.dto.JobResponseDTO;
import com.Application.JobTracker.entity.Job;
import com.Application.JobTracker.entity.JobStatus;
import com.Application.JobTracker.entity.UserPrincipal;
import org.springframework.stereotype.Service;

import java.util.List;


public interface JobService {
    JobResponseDTO CreateJob(JobRequestDTO job, UserPrincipal userPrincipal);

    List<Job> getAllJobs(UserPrincipal userPrincipal);

    Job updateJob(Long job_id, JobRequestDTO jobRequestDTO, UserPrincipal userPrincipal);

    void deleteJob(Long job_id, UserPrincipal userPrincipal);

    List<Job> searchJobs(String keywords, UserPrincipal principal);

    List<Job> SearchByJobStatus(JobStatus status, UserPrincipal userPrincipal);
}



