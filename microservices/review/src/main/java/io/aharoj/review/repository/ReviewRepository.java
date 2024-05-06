package io.aharoj.review.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.aharoj.review.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByCompanyId(Long companyId);
}
