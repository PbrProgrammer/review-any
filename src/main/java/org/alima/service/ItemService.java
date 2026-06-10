package org.alima.service;


import lombok.RequiredArgsConstructor;
import org.alima.dto.CategoryDto;
import org.alima.dto.ItemDto;
import org.alima.mapper.ItemMapper;
import org.alima.model.Category;
import org.alima.model.Item;
import org.alima.model.User;
import org.alima.repository.ItemRepository;
import org.alima.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final CategoryService categoryService;
    private final UserRepository userRepository;

//    public Item createItem(ItemRequest request) {
//        // ۱. پیدا کردن نام کاربری که لاگین کرده (از طریق SecurityContext)
//        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User user = userRepository.findByUsername(username)
//                .orElseThrow(() -> new RuntimeException("کاربر پیدا نشد"));
//
//
//        // ۳. ساخت و ذخیره ریویو
//        Item item = new Item();
//        item.setUser(user);
//        item.setItem(item);
//        item.setRating(request.getRating());
//        item.setContent(request.getContent());
//        item.setCreatedAt(LocalDateTime.now());
//
//        return ItemRepository.save(Item);
//    }

    public Page<ItemDto> getItems(int page, int size) {
        // نمایش نظرات بر اساس جدیدترین‌ها (صفحه‌بندی شده)
        return itemRepository.findAll(
                PageRequest.of(page, size, Sort.by("createdAt").descending())).map(ItemMapper::toDto);
    }

    public Page<ItemDto> getItemsByCategory(String categoryName, int page, int size) {
        CategoryDto categoryByName = categoryService.findCategoryByName(categoryName);
        return itemRepository.findByCategories_Id(categoryByName.getId(),
                PageRequest.of(page, size, Sort.by("createdAt").descending())).map(ItemMapper::toDto);
    }


}

