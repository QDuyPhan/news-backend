package com.quangduy.newsbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quangduy.newsbackend.entity.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, String> {}
