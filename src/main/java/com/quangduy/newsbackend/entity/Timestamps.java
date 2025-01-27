package com.quangduy.newsbackend.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Timestamps {
    @Column(name = "created_at")
    LocalDateTime created_at;

    @Column(name = "updated_at")
    LocalDateTime updated_at;
}
