package com.Application.JobTracker.service.impl;

import com.Application.JobTracker.entity.Job;
import com.Application.JobTracker.entity.JobStatus;
import com.Application.JobTracker.exception.ResourceNotFoundException;
import com.Application.JobTracker.repository.JobRepository;
import com.Application.JobTracker.service.JobService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;
    public Job CreateJob(Job job) {
//        Job newJob= Job.builder()
//                .job_id(job.getJob_id())
//                .role(job.getRole())
//                .jobStatus(job.getJobStatus())
//                .appliedDate(job.getAppliedDate())
//                .interviewDate(job.getInterviewDate())
//                .notes(job.getNotes())
//                .user(job.getUser())
//                .build();

        return jobRepository.save(job);
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public Job updateJob(Long job_id, Job job) {
        Job existing=jobRepository.findById(job_id).orElseThrow(() -> new ResourceNotFoundException("Job not found"));

        existing.setJobStatus(job.getJobStatus());
        existing.setCompanyName(job.getCompanyName());
        existing.setNotes(job.getNotes());
        existing.setRole(job.getRole());
        existing.setAppliedDate(job.getAppliedDate());
        existing.setInterviewDate(job.getInterviewDate());

        return jobRepository.save(existing) ;
    }

    public void deleteJob(Long job_id) {
         jobRepository.deleteById(job_id);
    }

    public List<Job> searchJobs(String keywords) {
        return jobRepository.findByCompanyNameContainingIgnoreCase(keywords);
    }

    public List<Job> SearchByJobStatus(JobStatus status) {
        return jobRepository.findByJobStatus(status);
    }
}
