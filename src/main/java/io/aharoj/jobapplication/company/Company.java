package io.aharoj.jobapplication.company;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.aharoj.jobapplication.job.Job;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Company {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String description;

  // we mapped the company field in the Job class to this field 
  @JsonIgnore
  @OneToMany(mappedBy = "company")
  private List<Job> jobs;
  // private List<Review> reviews;

  public Company() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<Job> getJobs() {
    return jobs;
  }

  public void setJobs(List<Job> jobs) {
    this.jobs = jobs;
  }

}
