package io.aharoj.jobapplication.job.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import io.aharoj.jobapplication.job.Job;
import io.aharoj.jobapplication.job.JobRepository;
import io.aharoj.jobapplication.job.JobService;

@Service
public class JobServiceImpl implements JobService {
  // private List<Job> jobs = new ArrayList<>();
  JobRepository jobRepository;

  // EVC
  public JobServiceImpl(JobRepository jobRepository) {
    this.jobRepository = jobRepository;
  }

  @Override
  public List<Job> findAll() {
    return jobRepository.findAll();
  }

  @Override
  public void createJob(Job job) {
    // // Check if the company exists
    // Optional<Company> company = companyRepository.findById(job.getCompanyId());
    // if (!company.isPresent()) {
    // throw new SomeException("Company with ID " + job.getCompanyId() + " does not
    // exist.");
    // }
    jobRepository.save(job);
  }

  @Override
  public Job getJobById(Long id) {
    return jobRepository.findById(id).orElse(null);
  }

  @Override
  public boolean deleteJobById(Long id) {
    try {
      jobRepository.deleteById(id);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  @Override
  public boolean updateJobById(Long id, Job updatedJob) {
    Optional<Job> jobOptional = jobRepository.findById(id);
    if (jobOptional.isPresent()) {
      Job job = jobOptional.get();
      job.setTitle(updatedJob.getTitle());
      job.setDescription(updatedJob.getDescription());
      job.setMinSalary(updatedJob.getMinSalary());
      job.setMaxSalary(updatedJob.getMaxSalary());
      job.setLocation(updatedJob.getLocation());
      jobRepository.save(job);
      return true;
    }
    return false;
  }

}
