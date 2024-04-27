package io.aharoj.jobapplication.job;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * JobController
 */

@RestController
public class JobController {
  public JobController(JobService jobService) {
    this.jobService = jobService;
  }

  private JobService jobService;

  @GetMapping("/jobs")
  public ResponseEntity<List<Job>> findAll() {
    return ResponseEntity.ok(jobService.findAll());
  }

  @PostMapping("/jobs")
  public HttpEntity<String> createJob(@RequestBody Job job) {
    jobService.createJob(job);
    return new ResponseEntity<>("Job added succesfully", HttpStatus.OK);
  }

  @GetMapping("/jobs/{id}")
  public HttpEntity<Job> getJobById(@PathVariable Long id) {
    Job job = jobService.getJobById(id);
    if (job != null) {
      return new ResponseEntity<>(job, HttpStatus.OK);
    }
    return new ResponseEntity<>(job, HttpStatus.NOT_FOUND);
  }

  @DeleteMapping("/jobs/{id}")
  public HttpEntity<String> deleteJob(@PathVariable Long id) {
    boolean deleted = jobService.deleteJobById(id);
    if (deleted) {
      return new ResponseEntity<>("Job Deleted", HttpStatus.OK);
    }
    return new ResponseEntity<>("Job Not Found", HttpStatus.NOT_FOUND);
  }
  // public HttpEntity<String> deleteJobById(@PathVariable Long id) {
  // if (jobService.deleteJobById(id)) {
  // return new ResponseEntity<>("Job deleted succesfully", HttpStatus.OK);
  // }
  // return new ResponseEntity<>("Job not found", HttpStatus.NOT_FOUND);
  // }

}
