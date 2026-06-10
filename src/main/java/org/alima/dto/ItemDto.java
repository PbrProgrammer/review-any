package org.alima.dto;

import lombok.Data;
import org.alima.model.Images;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ItemDto {

    private Long id;

    private Integer rating;

    private String content;
    private String title;

    private LocalDateTime createdAt;

    private List<Images> images;
}
