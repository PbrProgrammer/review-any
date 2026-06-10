package org.alima.controller;


import org.alima.dto.ReviewDto;
import org.alima.dto.ReviewRequest;
import org.alima.model.Review;
import org.alima.service.ReviewService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // ۱. ثبت ریویوی جدید (نیاز به توکن JWT در هدر درخواست دارد)
    @PostMapping
    public ResponseEntity<ReviewDto> createReview(@RequestBody ReviewRequest request) {
        return ResponseEntity.ok(reviewService.createReview(request));
    }

    // ۲. نمایش لیست ریویوهای یک محصول (دسترسی بدون توکن و عمومی)
    @GetMapping("/item/{itemId}")
    public ResponseEntity<Page<ReviewDto>> getReviews(
            @PathVariable Long itemId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(reviewService.getReviewsByItem(itemId, page, size));
    }
}
