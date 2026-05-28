package com.Application.JobTracker.controller;

import com.Application.JobTracker.dto.ApiResponse;
import com.Application.JobTracker.dto.JobRequestDTO;
import com.Application.JobTracker.dto.JobResponseDTO;
import com.Application.JobTracker.entity.Job;
import com.Application.JobTracker.entity.JobStatus;
import com.Application.JobTracker.entity.UserPrincipal;
import com.Application.JobTracker.service.JobService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/job")
public class JobController {
    private final JobService jobService;

    @PostMapping("/create")
    public JobResponseDTO createJob(@Valid @RequestBody JobRequestDTO jobRequestDTO, @AuthenticationPrincipal UserPrincipal principal){

        return jobService.CreateJob(jobRequestDTO,principal);

    }

    @GetMapping("/csrf-token")
    public CsrfToken getcsrfToken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @GetMapping("/alljobs")
    public ApiResponse<List<Job>>  allJobs(@AuthenticationPrincipal UserPrincipal principal){
        return ApiResponse.<List<Job>>builder()
                .Success(true)
                .message("these are all the jobs")
                .data(jobService.getAllJobs(principal))
                .build();
    }

    @PutMapping("/update/{id}")
    public ApiResponse<Job> updateJob(@PathVariable Long id, @Valid @RequestBody JobRequestDTO jobRequestDTO, @AuthenticationPrincipal UserPrincipal principal){

        return ApiResponse.<Job>builder()
                .Success(true)
                .message("updated successfully")
                .data(jobService.updateJob(id,jobRequestDTO,principal))
                .build();
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<Job> deleteJob(@PathVariable Long id, @AuthenticationPrincipal UserPrincipal principal){
        jobService.deleteJob(id, principal);
        return ApiResponse.<Job>builder()
                .Success(true)
                .message("deleted successfully")
                .data(null)
                .build();
    }

    @GetMapping("/search")
    public ApiResponse<List<Job>> searchByCompanyName(@RequestParam String keyword, @AuthenticationPrincipal UserPrincipal principal){
        return ApiResponse.<List<Job>>builder()
                .Success(true)
                .message("successfully retrieved")
                .data(jobService.searchJobs(keyword,principal))
                .build();
    }

    @GetMapping("/status/{status}")
    public ApiResponse<List<Job>> searchByStatus(@PathVariable JobStatus status, @AuthenticationPrincipal UserPrincipal principal){
        return ApiResponse.<List<Job>>builder()
                .Success(true)
                .message("successfully retrieved")
                .data(jobService.SearchByJobStatus(status, principal))
                .build();
    }


}
