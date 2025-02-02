package com.quangduy.newsbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quangduy.newsbackend.entity.News;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {}
