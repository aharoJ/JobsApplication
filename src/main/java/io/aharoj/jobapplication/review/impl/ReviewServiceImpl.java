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
  public List<Review> getAllReviews(Long companyId) {
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
  public boolean deleteReview(Long companyId, Long reviewId) {
    if (companyService.getCompanyById(companyId) != null && reviewRepository.existsById(reviewId)) {
      Review review = reviewRepository.findById(reviewId).orElse(null); // getReview(companyId, reviewId);
      Company company = review.getCompany(); // then get the company
      company.getReviews().remove(review); // remove the review from the company
      review.setCompany(null); // set the review's company to null
      companyService.updateCompany(companyId, company); // update the company
      reviewRepository.deleteById(reviewId); // delete the review
      return true;
    }
    return false;
  }

  @Override
  public boolean updateReview(Long companyId, Long reviewId, Review review) {
    if (companyService.getCompanyById(companyId) != null) {
      Review uppdatedReview = getReview(companyId, reviewId);
      if (uppdatedReview != null) {
        uppdatedReview.setTitle(review.getTitle());
        uppdatedReview.setDescription(review.getDescription());
        uppdatedReview.setRating(review.getRating());
        reviewRepository.save(uppdatedReview);
        return true;
      }
    }
    return false;
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
