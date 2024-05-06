package io.aharoj.job.service;

import java.util.List;

import io.aharoj.job.model.Job;

public interface JobService {

  boolean updateJobByID = false;

  List<Job> findAll();

  void createJob(Job job);

  Job getJobById(Long id);

  boolean deleteJobById(Long id);

  boolean updateJobById(Long id, Job updateJob);

}
