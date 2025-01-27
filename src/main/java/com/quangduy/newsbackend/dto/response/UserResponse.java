package com.quangduy.newsbackend.dto.response;

import java.util.Set;

import com.quangduy.newsbackend.entity.Timestamps;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    String id;
    String name;
    String username;
    String email;
    String password;
//    Set<RoleResponse> roles;
    Timestamps timestamps;
}
