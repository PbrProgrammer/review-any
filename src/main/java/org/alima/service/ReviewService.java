package org.alima.service;


import org.alima.dto.ReviewDto;
import org.alima.dto.ReviewRequest;
import org.alima.mapper.ReviewMapper;
import org.alima.model.Item;
import org.alima.model.Review;
import org.alima.model.User;
import org.alima.repository.ItemRepository;
import org.alima.repository.ReviewRepository;
import org.alima.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    public ReviewService(ReviewRepository rr, ItemRepository ir, UserRepository ur) {
        this.reviewRepository = rr;
        this.itemRepository = ir;
        this.userRepository = ur;
    }

    public Review createReview(ReviewRequest request) {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("کاربر پیدا نشد"));

        Item item = itemRepository.findById(request.getItemId())
                .orElseThrow(() -> new RuntimeException("ایتم مورد نظر یافت نشد"));

        Review review = new Review();
        review.setUser(user);
        review.setItem(item);
        review.setRating(request.getRating());
        review.setContent(request.getContent());
        review.setCreatedAt(LocalDateTime.now());

        return reviewRepository.save(review);
    }

    public Page<ReviewDto> getReviewsByItem(Long itemId, int page, int size) {
        return reviewRepository.findByItemId(
                itemId,
                PageRequest.of(page, size, Sort.by("createdAt").descending())).map(ReviewMapper::toDto);
    }
}

