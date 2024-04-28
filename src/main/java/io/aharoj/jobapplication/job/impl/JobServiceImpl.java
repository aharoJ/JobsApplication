package io.aharoj.jobapplication.job.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import io.aharoj.jobapplication.job.Job;
import io.aharoj.jobapplication.job.JobService;

/**
 * JobServiceImpl
 * this promomotes loose coupling between the controller and the service
 */
@Service
public class JobServiceImpl implements JobService {
  private List<Job> jobs = new ArrayList<>();
  private Long nextId = 1L;

  @Override
  public List<Job> findAll() {
    return jobs;
  }

  @Override
  public void createJob(Job job) {
    job.setId(nextId++);
    jobs.add(job);
  }

  @Override
  public Job getJobById(Long id) {
    for (Job job : jobs) {
      if (job.getId().equals(id)) {
        return job;
      }
    }
    return null;
  }

  @Override
  public boolean deleteJobById(Long id) {
    for (Job job : jobs) {
      if (job.getId().equals(id)) {
        jobs.remove(job);
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean updateJobById(Long id, Job updatedJob) {
    for (Job job : jobs) {
      if (job.getId().equals(id)) {
        job.setTitle(updatedJob.getTitle());
        job.setDescription(updatedJob.getDescription());
        job.setMinSalary(updatedJob.getMinSalary());
        job.setMaxSalary(updatedJob.getMaxSalary());
        job.setLocation(updatedJob.getLocation());
        return true;
      }
    }
    return false;
  }
}
