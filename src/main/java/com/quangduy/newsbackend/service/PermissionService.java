package com.quangduy.newsbackend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.quangduy.newsbackend.dto.request.PermissionRequest;
import com.quangduy.newsbackend.dto.response.PermissionResponse;
import com.quangduy.newsbackend.entity.Permission;
import com.quangduy.newsbackend.mapper.PermissionMapper;
import com.quangduy.newsbackend.repository.PermissionRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionService {
    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    public PermissionResponse createPermission(PermissionRequest permissionRequest) {
        Permission permission = permissionMapper.toPermission(permissionRequest);
        permission = permissionRepository.save(permission);
        return permissionMapper.toPermissionResponse(permission);
    }

    public List<PermissionResponse> getAllPermissions() {
        return permissionRepository.findAll().stream()
                .map(permissionMapper::toPermissionResponse)
                .toList();
    }

    public void deletePermission(String permission) {
        permissionRepository.deleteById(permission);
    }
}
