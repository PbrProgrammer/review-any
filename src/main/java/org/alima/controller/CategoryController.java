package org.alima.controller;


import lombok.RequiredArgsConstructor;
import org.alima.dto.CategoryDto;
import org.alima.dto.ItemDto;
import org.alima.service.CategoryService;
import org.alima.service.ItemService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<Page<CategoryDto>> getReviews(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(categoryService.getCategory(page, size));
    }
}
