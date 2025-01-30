package com.quangduy.newsbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quangduy.newsbackend.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {}
