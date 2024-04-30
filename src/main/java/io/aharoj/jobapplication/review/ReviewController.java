package io.aharoj.jobapplication.review;

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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {
  private ReviewService reviewService;

  // EVC
  public ReviewController(ReviewService reviewService) {
    this.reviewService = reviewService;
  }

  @GetMapping("/reviews")
  public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId) {
    return new ResponseEntity<>(reviewService.getReviews(companyId), HttpStatus.OK);
  }

  @PostMapping("/reviews")
  public ResponseEntity<String> createReview(@PathVariable Long companyId, @RequestBody Review review) {
    boolean isReviewSaved = reviewService.addReview(companyId, review);
    if (isReviewSaved) {
      return new ResponseEntity<>("Review created successfully", HttpStatus.OK);
    }
    return new ResponseEntity<>("Review NOT saved", HttpStatus.NOT_FOUND);
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
