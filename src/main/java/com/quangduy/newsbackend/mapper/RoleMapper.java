package com.quangduy.newsbackend.mapper;

import com.quangduy.newsbackend.dto.request.RoleRequest;
import com.quangduy.newsbackend.dto.response.RoleResponse;
import com.quangduy.newsbackend.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest roleRequest);

    RoleResponse toRoleResponse(Role role);
}
