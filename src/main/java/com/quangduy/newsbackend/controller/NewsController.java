package com.quangduy.newsbackend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.quangduy.newsbackend.dto.request.NewsRequest;
import com.quangduy.newsbackend.dto.response.ApiResponse;
import com.quangduy.newsbackend.dto.response.NewsResponse;
import com.quangduy.newsbackend.service.NewsService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NewsController {
    NewsService newsService;

    @PostMapping
    ApiResponse<NewsResponse> createCategory(@RequestBody NewsRequest newsRequest) {
        return ApiResponse.<NewsResponse>builder()
                .result(newsService.createNews(newsRequest))
                .build();
    }

    @GetMapping
    ApiResponse<List<NewsResponse>> getAllCategories() {
        return ApiResponse.<List<NewsResponse>>builder()
                .result(newsService.getAllNews())
                .build();
    }

    @DeleteMapping("/{newId}")
    ApiResponse<Void> deleteCategory(@PathVariable Long newId) {
        newsService.deleteNews(newId);
        return ApiResponse.<Void>builder().build();
    }
}
