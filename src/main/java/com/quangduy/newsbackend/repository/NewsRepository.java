package com.quangduy.newsbackend.repository;

import com.quangduy.newsbackend.dto.response.NewsResponse;
import com.quangduy.newsbackend.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.quangduy.newsbackend.entity.News;

import java.util.List;
import java.util.Set;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    @Query("SELECT n FROM News n JOIN n.categories c WHERE c.name = :categoryName")
    List<News> findByCategoryName(@Param("categoryName") String categoryName);
}
