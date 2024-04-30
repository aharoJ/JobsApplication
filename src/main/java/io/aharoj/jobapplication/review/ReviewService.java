package io.aharoj.jobapplication.review;

import java.util.List;

public interface ReviewService {
  List<Review> getReviews(Long companyId);

  boolean addReview(Long companyId, Review review);

  Review getReviewById(Long id);

  void deleteReview(Long id);

  Review updateReview(Long id, Review review);
}
