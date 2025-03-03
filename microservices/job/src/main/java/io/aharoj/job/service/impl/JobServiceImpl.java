package io.aharoj.job.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.aharoj.job.dto.JobWithCompanyDTO;
import io.aharoj.job.external.Company;
import io.aharoj.job.model.Job;
import io.aharoj.job.repository.JobRepository;
import io.aharoj.job.service.JobService;

@Service
public class JobServiceImpl implements JobService {
  // private List<Job> jobs = new ArrayList<>();
  JobRepository jobRepository;

  // EVC
  public JobServiceImpl(JobRepository jobRepository) {
    this.jobRepository = jobRepository;
  }

  @Override
  public List<JobWithCompanyDTO> findAll() {
    List<Job> jobs = jobRepository.findAll();
    List<JobWithCompanyDTO> jobWithCompanyDTOS = new ArrayList<>();

    return jobs.stream()
        .map(this::convertToDTO)
        .collect(Collectors.toList());

  }

  private JobWithCompanyDTO convertToDTO(Job job) {
    JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO();
    jobWithCompanyDTO.setJob(job);

    RestTemplate restTemplate = new RestTemplate();
    Company company = restTemplate.getForObject(
        "http://localhost:8081/companies/" + job.getCompanyId(),
        Company.class);

    jobWithCompanyDTO.setCompany(company);
    return jobWithCompanyDTO;

  }

  @Override
  public void createJob(Job job) {
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
