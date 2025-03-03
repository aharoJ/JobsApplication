package io.aharoj.monolithic.job;

import java.util.List;

/**
 * JobService
 */
public interface JobService {

  boolean updateJobByID = false;

  List<Job> findAll();

  void createJob(Job job);

  Job getJobById(Long id);

  boolean deleteJobById(Long id);

  boolean updateJobById(Long id, Job updateJob);

}
