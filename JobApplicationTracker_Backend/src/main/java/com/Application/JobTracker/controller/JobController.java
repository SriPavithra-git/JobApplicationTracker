package com.Application.JobTracker.controller;

import com.Application.JobTracker.entity.Job;
import com.Application.JobTracker.entity.JobStatus;
import com.Application.JobTracker.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/job")
public class JobController {
    private final JobService jobService;

    @PostMapping("/create")
    public Job createJob(@RequestBody Job job){
        return jobService.CreateJob(job);
    }

    @GetMapping("/alljobs")
    public List<Job>  allJobs(){
        return jobService.getAllJobs();
    }

    @PutMapping("/update/{id}")
    public Job updateJob(@PathVariable Long id, @RequestBody Job job){
        return jobService.updateJob(id,job);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteJob(@PathVariable Long id){
        jobService.deleteJob(id);
    }

    @GetMapping("/search")
    public List<Job> searchByCompanyName(@RequestParam String keyword){
        return jobService.searchJobs(keyword);
    }

    @GetMapping("/status/{status}")
    public List<Job> searchByStatus(@PathVariable JobStatus status){
        return jobService.SearchByJobStatus(status);
    }


}
