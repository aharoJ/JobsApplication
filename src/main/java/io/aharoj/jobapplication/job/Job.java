package io.aharoj.jobapplication.job;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Job
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Job {
  private Long id;
  private String title;
  private String description;
  private String minSalary;
  private String maxSalary;
  private String location;

}
