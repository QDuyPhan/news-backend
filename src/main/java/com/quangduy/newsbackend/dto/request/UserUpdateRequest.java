package com.quangduy.newsbackend.dto.request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {
    String name;

    @Email
    String email;

    @Size(min = 8, max = 50, message = "PASSWORD_INVALID")
    String password;

    LocalDateTime created_at;
    LocalDateTime updated_at;
}
