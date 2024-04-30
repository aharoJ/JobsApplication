package io.aharoj.jobapplication.review.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import io.aharoj.jobapplication.company.Company;
import io.aharoj.jobapplication.company.CompanyService;
import io.aharoj.jobapplication.review.Review;
import io.aharoj.jobapplication.review.ReviewRepository;
import io.aharoj.jobapplication.review.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {
  private final ReviewRepository reviewRepository;

  private final CompanyService companyService;

  // EVC
  public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
    this.reviewRepository = reviewRepository;
    this.companyService = companyService;
  }

  @Override
  public List<Review> getReviews(Long companyId) {
    List<Review> reviews = reviewRepository.findByCompanyId(companyId);
    return reviews;
  }

  @Override
  public boolean addReview(Long companyId, Review review) {
    Company company = companyService.getCompanyById(companyId);
    if (company != null) {
      review.setCompany(company);
      reviewRepository.save(review);
      return true;
    }
    return false;
  }

  @Override
  public Review getReviewById(Long id) {
    return reviewRepository.findById(id).orElse(null);
  }

  @Override
  public void deleteReview(Long id) {
    reviewRepository.deleteById(id);
  }

  @Override
  public Review updateReview(Long id, Review review) {
    Review modifyReview = reviewRepository.findById(id).orElse(null);
    if (modifyReview != null) {
      modifyReview.setTitle(review.getTitle());
      modifyReview.setDescription(review.getDescription());
      modifyReview.setRating(review.getRating());
      return reviewRepository.save(modifyReview);
    }
    throw new RuntimeException("Review not found");
  }

  @Override
  public Review getReview(Long companyId, Long reviewId) {
    List<Review> reviews = reviewRepository.findByCompanyId(companyId);
    for (Review review : reviews) {
      if (review.getId() == reviewId) {
        return review;
      }
    }
    return null;
  }
}
