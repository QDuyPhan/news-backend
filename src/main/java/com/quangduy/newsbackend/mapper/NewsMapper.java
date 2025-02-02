package com.quangduy.newsbackend.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.quangduy.newsbackend.dto.request.NewsRequest;
import com.quangduy.newsbackend.dto.response.NewsResponse;
import com.quangduy.newsbackend.entity.News;

@Mapper(componentModel = "spring")
public interface NewsMapper {
    @Mapping(target = "categories", ignore = true)
    News toNews(NewsRequest newsRequest);

    //    @Mapping(target = "categories", ignore = true)
    List<News> toNewsList(List<NewsRequest> newsRequests);

    NewsResponse toNewsResponse(News news);
}
