package io.aharoj.jobapplication.review.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import io.aharoj.jobapplication.review.Review;
import io.aharoj.jobapplication.review.ReviewRepository;
import io.aharoj.jobapplication.review.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {
  private ReviewRepository reviewRepository;

  // EVC
  public ReviewServiceImpl(ReviewRepository reviewRepository) {
    this.reviewRepository = reviewRepository;
  }

  @Override
  public List<Review> getReviews() {
    return reviewRepository.findAll();
  }

  @Override
  public void createReview(Review review) {
    reviewRepository.save(review);
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

}
