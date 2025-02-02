package com.quangduy.newsbackend.dto.request;

import java.time.LocalDateTime;
import java.util.Set;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NewsRequest {
    String title;

    String content;

    String image;

    LocalDateTime publishedAt;

    LocalDateTime created_at;

    LocalDateTime updated_at;

    Set<String> categories;
}
