package com.quangduy.newsbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quangduy.newsbackend.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {}
