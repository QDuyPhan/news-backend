package com.quangduy.newsbackend.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

import com.quangduy.newsbackend.entity.Timestamps;

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

    Timestamps timestamps;
}
