package com.quangduy.newsbackend.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Service;

import com.quangduy.newsbackend.dto.request.RoleRequest;
import com.quangduy.newsbackend.dto.response.RoleResponse;
import com.quangduy.newsbackend.mapper.RoleMapper;
import com.quangduy.newsbackend.repository.PermissionRepository;
import com.quangduy.newsbackend.repository.RoleRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService {
    RoleRepository roleRepository;
    RoleMapper roleMapper;
    PermissionRepository permissionRepository;

    public RoleResponse createRole(RoleRequest roleRequest) {
        var role = roleMapper.toRole(roleRequest);
        var permission = permissionRepository.findAllById(roleRequest.getPermissions());
        role.setPermissions(new HashSet<>(permission));
        role = roleRepository.save(role);
        return roleMapper.toRoleResponse(role);
    }

    public List<RoleResponse> getAllRoles() {
        return roleRepository.findAll().stream().map(roleMapper::toRoleResponse).toList();
    }

    public void deleteRole(String role) {
        roleRepository.deleteById(role);
    }
}
