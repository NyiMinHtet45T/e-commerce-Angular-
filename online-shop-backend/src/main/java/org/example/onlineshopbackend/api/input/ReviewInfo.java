package org.example.onlineshopbackend.api.input;

import org.example.onlineshopbackend.model.entity.Review;

import java.time.LocalDateTime;

public record ReviewInfo(
        Long id,
        int rating,
        String comment,
        LocalDateTime createAt,
        Long userId,
        Long productId,
        String userName
) {

    public Review getReview() {
        Review review = new Review();
        review.setId(id);
        review.setRating(rating);
        review.setComment(comment);
        review.setCreateAt(LocalDateTime.now());
        return review;
    }





}
