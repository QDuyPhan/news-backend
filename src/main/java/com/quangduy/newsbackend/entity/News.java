package com.quangduy.newsbackend.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

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

    @Embedded
    Timestamps timestamps;

    @ManyToMany
    Set<Tag> tags;

    @ManyToMany
    Set<Category> categories;
}
