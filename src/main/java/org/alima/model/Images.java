package org.alima.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "images")
@Data
public class Images {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl; // ذخیره آدرس فایل در MinIO/S3

    @ManyToOne
    @JoinColumn(name = "review_id")
    private Review review;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
}

