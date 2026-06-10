package org.alima.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "items")
@Data
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "items_category",  joinColumns = {@JoinColumn(name ="item_id" )}
    , inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;

    // فیلد برای میانگین امتیازات (برای کوئری‌های سریع)
    private Double averageRating = 0.0;
    private Integer reviewCount = 0;

    @Column(columnDefinition = "jsonb")
    private String features; // مثلا: {"parking": true, "pool": false, "gym": true}

    private LocalDateTime createdAt = LocalDateTime.now();
}


