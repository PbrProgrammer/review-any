package org.alima.service;


import lombok.RequiredArgsConstructor;
import org.alima.dto.CategoryDto;
import org.alima.dto.ItemDto;
import org.alima.mapper.CategoryMapper;
import org.alima.mapper.ItemMapper;
import org.alima.model.Category;
import org.alima.repository.CategoryRepository;
import org.alima.repository.ItemRepository;
import org.alima.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;


    public CategoryDto findCategoryByName(String categoryName) {
        Category category = categoryRepository.findCategoriesByTitle(categoryName).orElseThrow(() -> new RuntimeException("Category not found"));
        return CategoryMapper.toDto(category);
    }

    public Page<CategoryDto> getCategory(int page, int size) {
        return categoryRepository.findAll(
                PageRequest.of(page, size, Sort.by("id").descending())).map(CategoryMapper::toDto);
    }

}

