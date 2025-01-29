package org.example.onlineshopbackend.api;

import lombok.RequiredArgsConstructor;
import org.example.onlineshopbackend.api.input.ReviewInfo;
import org.example.onlineshopbackend.api.output.MessageResponse;
import org.example.onlineshopbackend.api.output.PageInfo;
import org.example.onlineshopbackend.api.output.TotalRatingAndReview;
import org.example.onlineshopbackend.model.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/review")
public class ReviewApi {

    private final ReviewService reviewService;

    @PostMapping("/")
    public ResponseEntity<MessageResponse> addReview(@RequestBody ReviewInfo reviewInfo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewService.addReview(reviewInfo));
    }

    @GetMapping("/")
    public ResponseEntity<PageInfo<ReviewInfo>> getAllReview(@RequestParam(required = false) Long productId,
                                                                    @RequestParam(required = false, defaultValue = "0") Integer page,
                                                                    @RequestParam(required = false, defaultValue = "5") Integer size) {
        return ResponseEntity.status(HttpStatus.OK).body(reviewService.getReview(productId, page, size));
    }

    @PutMapping("/")
    public ResponseEntity<MessageResponse> updateReview(@RequestBody ReviewInfo reviewInfo) {
        return ResponseEntity.status(HttpStatus.OK).body(reviewService.updateReview(reviewInfo));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<TotalRatingAndReview> getAvgRatingAndComment(@PathVariable Long productId) {
        return ResponseEntity.status(HttpStatus.OK).body(reviewService.getAvgRatingAndReview(productId));
    }



}
