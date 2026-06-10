package org.alima.controller;


import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.alima.dto.ItemDto;
import org.alima.dto.ReviewDto;
import org.alima.dto.ReviewRequest;
import org.alima.model.Review;
import org.alima.service.ItemService;
import org.alima.service.ReviewService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping()
    public ResponseEntity<Page<ItemDto>> getReviews(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(itemService.getItems(page, size));
    }

    @GetMapping("/{categoryName}")
    public ResponseEntity<Page<ItemDto>> getItemsByCategory(
            @PathVariable() String categoryName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(itemService.getItemsByCategory(categoryName, page, size));
    }
}
