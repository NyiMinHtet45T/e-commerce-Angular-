package org.example.onlineshopbackend.model.service;

import lombok.RequiredArgsConstructor;
import org.example.onlineshopbackend.api.input.ReviewInfo;
import org.example.onlineshopbackend.api.output.MessageResponse;
import org.example.onlineshopbackend.api.output.PageInfo;
import org.example.onlineshopbackend.api.output.TotalRatingAndReview;
import org.example.onlineshopbackend.exception.ApiBusinessException;
import org.example.onlineshopbackend.model.entity.Product;
import org.example.onlineshopbackend.model.entity.Review;
import org.example.onlineshopbackend.model.entity.User;
import org.example.onlineshopbackend.model.repo.ProductRepo;
import org.example.onlineshopbackend.model.repo.ReviewRepo;
import org.example.onlineshopbackend.model.repo.UserRepo;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepo reviewRepo;
    private final UserRepo userRepo;
    private final ProductRepo productRepo;

    public MessageResponse addReview(ReviewInfo reviewRequest) {
        reviewRepo.save(initReview(reviewRequest, true));
        return new MessageResponse("Successfully added review");
    }

    public PageInfo<ReviewInfo> getReview(Long productId, int page, int size) {

        return PageInfo.getPageInfo(reviewRepo.findByProductId(productId, PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createAt"))));
    }

    public MessageResponse updateReview(ReviewInfo reviewRequest) {
        reviewRepo.save(initReview(reviewRequest, false));
        return new MessageResponse("Successfully updated review");
    }

    public TotalRatingAndReview getAvgRatingAndReview(Long productId) {
        return reviewRepo.findTotalAndRating(productId).orElse(null);
    }

    private Review initReview(ReviewInfo reviewRequest, boolean isCreated) {
        Review review = reviewRequest.getReview();
        User user = userRepo.findById(reviewRequest.userId()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        review.setUser(user);
        Product product = productRepo.findById(reviewRequest.productId()).orElseThrow(() -> new ApiBusinessException("Product not found"));
        review.setProduct(product);
        if (isCreated) {
            user.addReview(review);
            product.addReview(review);
        }
        return review;
    }

}
