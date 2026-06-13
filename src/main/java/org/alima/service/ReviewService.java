package org.alima.service;


import lombok.RequiredArgsConstructor;
import org.alima.dto.ReviewDto;
import org.alima.dto.ReviewRequest;
import org.alima.exception.ResourceNotFoundException;
import org.alima.mapper.ReviewMapper;
import org.alima.model.Images;
import org.alima.model.Item;
import org.alima.model.Review;
import org.alima.model.User;
import org.alima.repository.ItemRepository;
import org.alima.repository.ReviewRepository;
import org.alima.security.UserRepository;
import org.alima.storage.StorageService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final ImageService imageService;
    private final StorageService storageService;

    public ReviewDto createReview(ReviewRequest request) {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("کاربر پیدا نشد", username));

        Item item = itemRepository.findById(request.getItemId())
                .orElseThrow(() -> new ResourceNotFoundException("ایتم مورد نظر یافت نشد", "4002"));

        Review review = new Review();
        review.setUser(user);
        review.setItem(item);
        review.setRating(request.getRating());
        review.setContent(request.getContent());
        review.setCreatedAt(LocalDateTime.now());

        List<Images> images = imageService.getImagesByImageUrl(request.getImageKeys());
        images.forEach(image -> {
            image.setReview(review);
        });
        review.setImages(images);

        Review save = reviewRepository.save(review);
        return ReviewMapper.toDto(save);
    }

    public Page<ReviewDto> getReviewsByItem(Long itemId, int page, int size) {
        return reviewRepository.findByItemId(
                itemId,
                PageRequest.of(page, size, Sort.by("createdAt").descending())).map(this::getReview);

    }

    public ReviewDto getReview(Review review) {

        List<Images> images =
                imageService.getImagesByReviewId(review.getId());

        List<String> urls = images.stream()
                .map(img -> storageService.generatePresignedUrl(
                        img.getImageUrl(),
                        60
                ))
                .toList();

        return ReviewMapper.toDto(review, urls);
    }

}

