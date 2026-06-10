package org.alima.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "reviews")
@Data
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private Integer rating; // مثلا از ۱ تا ۵

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    private List<Images> images;
}


