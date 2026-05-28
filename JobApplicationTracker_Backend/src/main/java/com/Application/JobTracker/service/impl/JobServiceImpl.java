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

    private User getUser(UserPrincipal userPrincipal){
        return  userRepository.findByEmail(userPrincipal.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    public JobResponseDTO CreateJob(JobRequestDTO jobRequestDTO, UserPrincipal principal) {
        User user=getUser(principal);
        Job newJob= Job.builder()
                .role(jobRequestDTO.getRole())
                .jobStatus(jobRequestDTO.getJobStatus())
                .appliedDate(jobRequestDTO.getAppliedDate())
                .interviewDate(jobRequestDTO.getInterviewDate())
                .notes(jobRequestDTO.getNotes())
                .companyName(jobRequestDTO.getCompanyName())
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

    public List<Job> getAllJobs(UserPrincipal userPrincipal) {

        return jobRepository.findByUser(getUser(userPrincipal));
    }



    public Job updateJob(Long job_id, JobRequestDTO jobRequestDTO, UserPrincipal userPrincipal) {
        User user=getUser(userPrincipal);
        Job existing=jobRepository.findById(job_id).orElseThrow(() -> new ResourceNotFoundException("Job not found"));
        if(!existing.getUser().getUser_id().equals(user.getUser_id())){
            throw new RuntimeException("Unauthorized");
        }
        existing.setJobStatus(jobRequestDTO.getJobStatus());
        existing.setCompanyName(jobRequestDTO.getCompanyName());
        existing.setNotes(jobRequestDTO.getNotes());
        existing.setRole(jobRequestDTO.getRole());
        existing.setAppliedDate(jobRequestDTO.getAppliedDate());
        existing.setInterviewDate(jobRequestDTO.getInterviewDate());

        return jobRepository.save(existing) ;
    }

    public void deleteJob(Long job_id, UserPrincipal userPrincipal) {
        User user=getUser(userPrincipal);
        Job existing=jobRepository.findById(job_id)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found"));
        if(!existing.getUser().getUser_id().equals(user.getUser_id())){
            throw new RuntimeException("Unauthorized");
        }
        jobRepository.deleteById(job_id);
    }

    public List<Job> searchJobs(String keywords, UserPrincipal userPrincipal) {
        return jobRepository.findByUserAndCompanyNameContainingIgnoreCase(getUser(userPrincipal), keywords);
    }

    public List<Job> SearchByJobStatus(JobStatus status, UserPrincipal userPrincipal) {
        return jobRepository.findByUserAndJobStatus(getUser(userPrincipal),status);
    }
}
