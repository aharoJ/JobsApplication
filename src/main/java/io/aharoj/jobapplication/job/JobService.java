package io.aharoj.jobapplication.job;

import java.util.List;

/**
 * JobService
 */
public interface JobService {

  List<Job> findAll();

  void createJob(Job job);

  Job getJobById(Long id);

}
