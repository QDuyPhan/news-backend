package com.quangduy.newsbackend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.quangduy.newsbackend.dto.request.RoleRequest;
import com.quangduy.newsbackend.dto.response.ApiResponse;
import com.quangduy.newsbackend.dto.response.RoleResponse;
import com.quangduy.newsbackend.service.RoleService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@RequestMapping("roles")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleController {
    RoleService roleService;

    @PostMapping
    ApiResponse<RoleResponse> createRole(@RequestBody RoleRequest roleRequest) {
        return ApiResponse.<RoleResponse>builder()
                .result(roleService.createRole(roleRequest))
                .build();
    }

    @GetMapping
    ApiResponse<List<RoleResponse>> getAllRoles() {
        return ApiResponse.<List<RoleResponse>>builder()
                .result(roleService.getAllRoles())
                .build();
    }

    @DeleteMapping("/{role}")
    ApiResponse<Void> deleteRole(@PathVariable("role") String role) {
        roleService.deleteRole(role);
        return ApiResponse.<Void>builder().build();
    }
}
