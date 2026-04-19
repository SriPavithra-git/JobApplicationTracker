package com.Application.JobTracker.service;

import com.Application.JobTracker.entity.Job;
import com.Application.JobTracker.entity.JobStatus;
import org.springframework.stereotype.Service;

import java.util.List;


public interface JobService {
    Job CreateJob(Job job);

    List<Job> getAllJobs();

    Job updateJob(Long job_id, Job job);

    void deleteJob(Long job_id);

    List<Job> searchJobs(String keywords);

    List<Job> SearchByJobStatus(JobStatus status);
}



