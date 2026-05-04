package com.Application.JobTracker.controller;

import com.Application.JobTracker.dto.ApiResponse;
import com.Application.JobTracker.dto.JobRequestDTO;
import com.Application.JobTracker.dto.JobResponseDTO;
import com.Application.JobTracker.entity.Job;
import com.Application.JobTracker.entity.JobStatus;
import com.Application.JobTracker.entity.UserPrincipal;
import com.Application.JobTracker.service.JobService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/job")
public class JobController {
    private final JobService jobService;

    @PostMapping("/create")
    public JobResponseDTO createJob(@RequestBody JobRequestDTO jobRequestDTO,   @AuthenticationPrincipal UserPrincipal principal){

        return jobService.CreateJob(jobRequestDTO,principal);

    }

    @GetMapping("/csrf-token")
    public CsrfToken getcsrfToken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @GetMapping("/alljobs")
    public ApiResponse<List<Job>>  allJobs(){
        return ApiResponse.<List<Job>>builder()
                .Success(true)
                .message("these are all the jobs")
                .data(jobService.getAllJobs())
                .build();
    }

    @PutMapping("/update/{id}")
    public ApiResponse<Job> updateJob(@PathVariable Long id, @RequestBody Job job){

        return ApiResponse.<Job>builder()
                .Success(true)
                .message("updated successfully")
                .data(jobService.updateJob(id,job))
                .build();
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<Job> deleteJob(@PathVariable Long id){
        jobService.deleteJob(id);
        return ApiResponse.<Job>builder()
                .Success(true)
                .message("deleted successfully")
                .data(null)
                .build();
    }

    @GetMapping("/search")
    public ApiResponse<List<Job>> searchByCompanyName(@RequestParam String keyword){
        return ApiResponse.<List<Job>>builder()
                .Success(true)
                .message("successfully retrieved")
                .data(jobService.searchJobs(keyword))
                .build();
    }

    @GetMapping("/status/{status}")
    public ApiResponse<List<Job>> searchByStatus(@PathVariable JobStatus status){
        return ApiResponse.<List<Job>>builder()
                .Success(true)
                .message("successfully retrieved")
                .data(jobService.SearchByJobStatus(status))
                .build();
    }


}
