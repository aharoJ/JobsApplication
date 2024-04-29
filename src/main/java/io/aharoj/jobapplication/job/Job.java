package io.aharoj.jobapplication.job;

import io.aharoj.jobapplication.company.Company;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
@Entity
// @Table(name="job_table")
public class Job {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  private String description;
  private String minSalary;
  private String maxSalary;
  private String location;

  @ManyToOne
  private Company company;
}
