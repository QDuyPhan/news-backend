package com.quangduy.newsbackend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.quangduy.newsbackend.dto.request.CategoryRequest;
import com.quangduy.newsbackend.dto.response.CategoryResponse;
import com.quangduy.newsbackend.entity.Category;
import com.quangduy.newsbackend.mapper.CategoryMapper;
import com.quangduy.newsbackend.repository.CategoryRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryService {
    CategoryRepository categoryRepository;
    CategoryMapper categoryMapper;

    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
        Category category = categoryMapper.toCategory(categoryRequest);
        category = categoryRepository.save(category);
        return categoryMapper.toCategoryResponse(category);
    }

    public List<CategoryResponse> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::toCategoryResponse)
                .toList();
    }

    public void deletePermission(String categoryName) {
        categoryRepository.deleteById(categoryName);
    }
}
