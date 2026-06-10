package org.alima.repository;


import org.alima.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

    Page<Item> findByCategories_Id(Long categoryId, Pageable pageable);

}
