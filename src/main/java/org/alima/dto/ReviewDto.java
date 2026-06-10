package org.alima.dto;

import lombok.Data;
import org.alima.model.Item;
import org.alima.model.Images;
import org.alima.model.User;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ReviewDto {

    private Long id;

    private User user;

    private Item item;

    private Integer rating;

    private String content;

    private LocalDateTime createdAt;

    private List<Images> images;
}
