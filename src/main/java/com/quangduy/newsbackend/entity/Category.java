package com.quangduy.newsbackend.entity;

import jakarta.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "categories")
public class Category {
    @Id
    @Column(name = "name", nullable = false, unique = true)
    String name;

    @Column(name = "description")
    String description;
}
