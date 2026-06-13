package org.alima.repository;


import org.alima.model.Images;
import org.alima.model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<Images, Long> {

    List<Images> findImagesByReviewId(Long reviewId);

    List<Images> findImagesByImageUrlIn(Collection<String> imageUrls);
}
