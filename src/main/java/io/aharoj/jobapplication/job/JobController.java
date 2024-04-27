package io.aharoj.jobapplication.job;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

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
  // whatever path variable is passed in the url, it will be passed to the method
  public HttpEntity<Job> getJobById(@PathVariable Long id) {
    // HttpStatus
    Job job = jobService.getJobById(id);
    if (job != null) {
      return new ResponseEntity<>(job, HttpStatus.OK);
    }
    return new ResponseEntity<>(job, HttpStatus.NOT_FOUND);
    // return new Job(0L, "Job not found", "Job not found", "0", "0", "0");
  }

}
