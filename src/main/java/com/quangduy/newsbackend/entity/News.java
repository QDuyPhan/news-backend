package com.quangduy.newsbackend.entity;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "news")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class News implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "title")
    String title;

    @Column(name = "content")
    String content;

    @Column(name = "image")
    String image;

    @Column(name = "published_at")
    LocalDateTime publishedAt;

    @Column(name = "created_at")
    LocalDateTime created_at;

    @Column(name = "updated_at")
    LocalDateTime updated_at;

    //    @ManyToMany
    //    Set<Tag> tags;

    @ManyToMany
    Set<Category> categories;
}
