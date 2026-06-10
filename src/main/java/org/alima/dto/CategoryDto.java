package org.alima.dto;

import lombok.Data;
import org.alima.model.Images;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CategoryDto {

    private Long id;
    private String title;
    private String description;
}
