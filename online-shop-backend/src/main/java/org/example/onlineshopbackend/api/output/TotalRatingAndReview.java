package org.example.onlineshopbackend.api.output;

public record TotalRatingAndReview(
        Double avgRating,
        Long totalComment
) {
}
