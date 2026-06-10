package org.alima.mapper;

import org.alima.dto.ReviewDto;
import org.alima.model.Review;

public class ReviewMapper {

    public static ReviewDto toDto(Review review) {
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setId(review.getId());
        reviewDto.setContent(review.getContent());
        reviewDto.setCreatedAt(review.getCreatedAt());
//        reviewDto.setUser(review.getUser());
        return reviewDto;

    }
}
