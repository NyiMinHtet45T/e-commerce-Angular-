package org.example.onlineshopbackend.model.repo;

import org.example.onlineshopbackend.api.input.ReviewInfo;
import org.example.onlineshopbackend.api.output.TotalRatingAndReview;
import org.example.onlineshopbackend.model.entity.Review;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ReviewRepo extends JpaRepository<Review, Long> {

    @Query("""
select new org.example.onlineshopbackend.api.input.ReviewInfo(r.id, r.rating, r.comment, r.createAt, r.user.id, r.product.id, r.user.name) from Review r where r.product.id = :productId
""")
    Page<ReviewInfo> findByProductId(Long productId, Pageable pageable);

    @Query("""
select new org.example.onlineshopbackend.api.output.TotalRatingAndReview(avg (r.rating), count (r.comment)) from Review r where r.product.id = :productId
""")
    Optional<TotalRatingAndReview> findTotalAndRating(Long productId);
}
