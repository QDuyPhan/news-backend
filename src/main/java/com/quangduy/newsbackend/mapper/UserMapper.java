package com.quangduy.newsbackend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.quangduy.newsbackend.dto.request.UserCreationRequest;
import com.quangduy.newsbackend.dto.request.UserUpdateRequest;
import com.quangduy.newsbackend.dto.response.UserResponse;
import com.quangduy.newsbackend.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);

    UserResponse toUserResponse(User user);

    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
