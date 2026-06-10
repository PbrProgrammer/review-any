package org.alima.dto;


import lombok.Data;

@Data
public class ReviewRequest {
    private Long itemId;
    private Integer rating;
    private String content;
}
