package com.quangduy.newsbackend.mapper;

import org.mapstruct.Mapper;

import com.quangduy.newsbackend.dto.request.PermissionRequest;
import com.quangduy.newsbackend.dto.response.PermissionResponse;
import com.quangduy.newsbackend.entity.Permission;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest permissionRequest);

    PermissionResponse toPermissionResponse(Permission permission);
}
