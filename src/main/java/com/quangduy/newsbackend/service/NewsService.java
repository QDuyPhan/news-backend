package com.quangduy.newsbackend.service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.exception.DataException;
import org.springframework.stereotype.Service;

import com.quangduy.newsbackend.dto.request.NewsRequest;
import com.quangduy.newsbackend.dto.response.NewsResponse;
import com.quangduy.newsbackend.entity.News;
import com.quangduy.newsbackend.exception.AppException;
import com.quangduy.newsbackend.exception.ErrorCode;
import com.quangduy.newsbackend.mapper.NewsMapper;
import com.quangduy.newsbackend.repository.CategoryRepository;
import com.quangduy.newsbackend.repository.NewsRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NewsService {
    NewsRepository newsRepository;
    CategoryRepository categoryRepository;
    NewsMapper newsMapper;

    public NewsResponse createNews(NewsRequest newsRequest) {
        try {
            News news = newsMapper.toNews(newsRequest);
            var category = categoryRepository.findAllById(newsRequest.getCategories());
            news.setCategories(new HashSet<>(category));
            newsRepository.save(news);
            return newsMapper.toNewsResponse(news);
        } catch (DataException e) {
            throw new AppException(ErrorCode.DATABASE_ERROR);
        }
    }

    public List<NewsResponse> getAllNews() {
        return newsRepository.findAll().stream().map(newsMapper::toNewsResponse).collect(Collectors.toList());
    }

    public void deleteNews(Long newsId) {
        newsRepository.deleteById(newsId);
    }
}
