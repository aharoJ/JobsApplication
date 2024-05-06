package io.aharoj.review.service;

import java.util.List;

import io.aharoj.review.model.Review;

public interface ReviewService {
  List<Review> getAllReviews(Long companyId);

  boolean addReview(Long companyId, Review review);

  Review getReview(Long reviewId);

  boolean deleteReview(Long reviewId);

  boolean updateReview(Long reviewId, Review review);
}
