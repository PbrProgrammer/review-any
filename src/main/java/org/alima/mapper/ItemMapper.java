package org.alima.mapper;

import org.alima.dto.ItemDto;
import org.alima.dto.ReviewDto;
import org.alima.model.Item;
import org.alima.model.Review;

public class ItemMapper {

    public static ItemDto toDto(Item item) {
        ItemDto itemDto = new ItemDto();
        itemDto.setId(item.getId());
        itemDto.setContent(item.getTitle());
        itemDto.setCreatedAt(item.getCreatedAt());
//        itemDto.setUser(item.getUser());
        return itemDto;

    }
}
