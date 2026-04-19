package com.Application.JobTracker.repository;

import com.Application.JobTracker.entity.Job;
import com.Application.JobTracker.entity.JobStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByJobStatus(JobStatus jobStatus);
    List<Job> findByCompanyNameContainingIgnoreCase(String companyName);

}
