package io.aharoj.jobapplication.review;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reviews")
public class ReviewController {
  private ReviewService reviewService;

  // EVC
  public ReviewController(ReviewService reviewService) {
    this.reviewService = reviewService;
  }

  @GetMapping
  public ResponseEntity<List<Review>> getReviews() {
    return new ResponseEntity<>(reviewService.getReviews(), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<String> createReview(@RequestBody Review review) {
    reviewService.createReview(review);
    return new ResponseEntity<>("Review created successfully", HttpStatus.OK);
  }

  @RequestMapping("/{id}")
  public ResponseEntity<Review> findReview(@PathVariable Long id) {
    if (reviewService.getReviewById(id) != null) {
      reviewService.getReviewById(id);
      return new ResponseEntity<>(reviewService.getReviewById(id), HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> removeReview(@PathVariable Long id) {
    reviewService.deleteReview(id);
    return new ResponseEntity<>("Review removed successfully", HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<String> updateReview(@PathVariable Long id, @RequestBody Review review) {
    try {
      reviewService.updateReview(id, review);
      return new ResponseEntity<>("Review updated sucessfully", HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("Review updated FAILED", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
