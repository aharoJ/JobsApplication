package io.aharoj.monolithic.company;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.aharoj.monolithic.job.Job;
import io.aharoj.monolithic.review.Review;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Company {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String description;

  @JsonIgnore
  @OneToMany(mappedBy = "company")
  private List<Job> jobs;

  // Now the reviews will be included in the response.
  @OneToMany(mappedBy = "company")
  private List<Review> reviews;
}
