package io.aharoj.monolithic.job;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobController {
  private JobService jobService;

  // EVC
  public JobController(JobService jobService) {
    this.jobService = jobService;
  }

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
  // @RequestMapping(value = "/jobs/{id}", method = RequestMethod.GET)
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
      return new ResponseEntity<>("Job deleted", HttpStatus.OK);
    }
    return new ResponseEntity<>("Job not Found", HttpStatus.NOT_FOUND);
  }

  @PutMapping("/jobs/{id}")
  public HttpEntity<String> updateJob(@PathVariable Long id, @RequestBody Job updateJob) {
    if (jobService.updateJobById(id, updateJob)) {
      return new ResponseEntity<>("Job updated sucessfully", HttpStatus.ACCEPTED);
    }
    return new ResponseEntity<>("Update failed", HttpStatus.NOT_FOUND);
  }
}
