package org.alima.mapper;

import org.alima.dto.ReviewDto;
import org.alima.model.Review;

import java.util.List;

public class ReviewMapper {

    public static ReviewDto toDto(Review review) {
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setId(review.getId());
        reviewDto.setContent(review.getContent());
        reviewDto.setCreatedAt(review.getCreatedAt());
//        reviewDto.setUser(review.getUser());
        return reviewDto;

    }
    public static ReviewDto toDto(Review review, List<String> images) {

        ReviewDto dto = new ReviewDto();
        dto.setId(review.getId());
        dto.setContent(review.getContent());
        dto.setCreatedAt(review.getCreatedAt());
        dto.setImagesUrl(images);

        return dto;
    }
}
