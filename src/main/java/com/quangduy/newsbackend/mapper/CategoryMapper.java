package com.quangduy.newsbackend.mapper;

import org.mapstruct.Mapper;

import com.quangduy.newsbackend.dto.request.CategoryRequest;
import com.quangduy.newsbackend.dto.response.CategoryResponse;
import com.quangduy.newsbackend.entity.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toCategory(CategoryRequest categoryRequest);

    CategoryResponse toCategoryResponse(Category category);
}
