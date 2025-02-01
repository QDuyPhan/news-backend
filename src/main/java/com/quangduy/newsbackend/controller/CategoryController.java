package com.quangduy.newsbackend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.quangduy.newsbackend.dto.request.CategoryRequest;
import com.quangduy.newsbackend.dto.response.ApiResponse;
import com.quangduy.newsbackend.dto.response.CategoryResponse;
import com.quangduy.newsbackend.service.CategoryService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryController {
    CategoryService categoryService;

    @PostMapping
    ApiResponse<CategoryResponse> createCategory(@RequestBody CategoryRequest categoryRequest) {
        return ApiResponse.<CategoryResponse>builder()
                .result(categoryService.createCategory(categoryRequest))
                .build();
    }

    @GetMapping
    ApiResponse<List<CategoryResponse>> getAllCategories() {
        return ApiResponse.<List<CategoryResponse>>builder()
                .result(categoryService.getAllCategories())
                .build();
    }

    @DeleteMapping("/{categoryName}")
    ApiResponse<Void> deleteCategory(@PathVariable String categoryName) {
        categoryService.deletePermission(categoryName);
        return ApiResponse.<Void>builder().build();
    }
}
