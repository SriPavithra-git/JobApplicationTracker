package com.Application.JobTracker.service.impl;

import com.Application.JobTracker.dto.JobRequestDTO;
import com.Application.JobTracker.dto.JobResponseDTO;
import com.Application.JobTracker.entity.Job;
import com.Application.JobTracker.entity.JobStatus;
import com.Application.JobTracker.entity.User;
import com.Application.JobTracker.entity.UserPrincipal;
import com.Application.JobTracker.exception.ResourceNotFoundException;
import com.Application.JobTracker.repository.JobRepository;
import com.Application.JobTracker.repository.UserRepository;
import com.Application.JobTracker.service.JobService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    public JobResponseDTO CreateJob(JobRequestDTO jobRequestDTO, UserPrincipal principal) {
        User user=userRepository.findByEmail(principal.getUsername()).get();
        Job newJob= Job.builder()
                .role(jobRequestDTO.getRole())
                .jobStatus(jobRequestDTO.getJobStatus())
                .appliedDate(jobRequestDTO.getAppliedDate())
                .interviewDate(jobRequestDTO.getInterviewDate())
                .notes(jobRequestDTO.getNotes())
                .user(user)
                .build();

        jobRepository.save(newJob);
        JobResponseDTO jobResponseDTO= JobResponseDTO.builder()
                .companyName(jobRequestDTO.getCompanyName())
                .role(jobRequestDTO.getRole())
                .appliedDate(jobRequestDTO.getAppliedDate())
                .interviewDate(jobRequestDTO.getInterviewDate())
                .jobStatus(jobRequestDTO.getJobStatus())
                .notes(jobRequestDTO.getNotes())
                .username(user.getUsername())
                .build();
        return jobResponseDTO;
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
