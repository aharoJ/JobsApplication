package io.aharoj.jobapplication.review;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.aharoj.jobapplication.company.Company;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Review {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  private String description;
  private double rating;

  // Many reviews can be written for one company
  @JsonIgnore
  @ManyToOne()
  private Company company;

}
