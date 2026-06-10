package org.alima.mapper;

import org.alima.dto.CategoryDto;
import org.alima.dto.ItemDto;
import org.alima.model.Category;
import org.alima.model.Item;

public class CategoryMapper {

    public static CategoryDto toDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setTitle(category.getTitle());
        categoryDto.setDescription(category.getDescription());
//        categoryDto.setUser(category.getUser());
        return categoryDto;

    }
}
