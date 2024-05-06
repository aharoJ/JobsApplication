package io.aharoj.review.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.aharoj.review.model.Review;
import io.aharoj.review.service.ReviewService;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
  private ReviewService reviewService;

  // EVC
  public ReviewController(ReviewService reviewService) {
    this.reviewService = reviewService;
  }

  @GetMapping
  public ResponseEntity<List<Review>> getAllReviews(@RequestParam Long companyId) {
    return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<String> addReview(@RequestParam Long companyId, @RequestBody Review review) {
    boolean isReviewSaved = reviewService.addReview(companyId, review);
    if (isReviewSaved) {
      return new ResponseEntity<>("Review created successfully", HttpStatus.OK);
    }
    return new ResponseEntity<>("Review NOT saved", HttpStatus.NOT_FOUND);
  }

  @GetMapping("/{reviewId}")
  public ResponseEntity<Review> getReview(@PathVariable Long reviewId) {
    return new ResponseEntity<>(reviewService.getReview(reviewId), HttpStatus.OK);
  }

  @PutMapping("/{reviewId}")
  public ResponseEntity<String> updateReview(@PathVariable Long reviewId,
      @RequestBody Review review) {
    boolean isReviewUpdated = reviewService.updateReview(reviewId, review);
    if (isReviewUpdated) {
      return new ResponseEntity<>("Review updated successfully", HttpStatus.OK);
    }
    return new ResponseEntity<>("Review NOT updated", HttpStatus.NOT_FOUND);
  }

  @DeleteMapping("/{reviewId}")
  public ResponseEntity<String> removeReview(@PathVariable Long reviewId) {
    boolean isDeleted = reviewService.deleteReview(reviewId);
    if (isDeleted) {
      return new ResponseEntity<>("Review removed successfully", HttpStatus.OK);
    }
    return new ResponseEntity<>("Review NOT found", HttpStatus.NOT_FOUND);
  }

}
