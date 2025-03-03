package io.aharoj.job.dto;

import io.aharoj.job.external.Company;
import io.aharoj.job.model.Job;

/**
 * what is the response we want
 * to send to the client when they request a job with company details
 */
public class JobWithCompanyDTO {
  private Job job;
  private Company company;

  public Job getJob() {
    return job;
  }

  public void setJob(Job job) {
    this.job = job;
  }

  public Company getCompany() {
    return company;
  }

  public void setCompany(Company company) {
    this.company = company;
  }

}
