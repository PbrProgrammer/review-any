package org.alima.dto;


import lombok.Data;

import java.util.List;

@Data
public class ReviewRequest {
    private Long itemId;
    private Integer rating;
    private String content;
    private List<String> imageKeys;

}
