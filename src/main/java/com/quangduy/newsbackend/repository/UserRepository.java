package com.quangduy.newsbackend.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.quangduy.newsbackend.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);

    //    User findByEmail(String email);
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    @Modifying
    @Query("UPDATE User u SET " +
            "u.name = COALESCE(:name, u.name), " +
            "u.email = COALESCE(:email, u.email), " +
            "u.password = COALESCE(:password, u.password), " +
            "u.updated_at = :updatedAt " +
            "WHERE u.id = :id")
    void update(
            @Param("id") String id,
            @Param("name") String name,
            @Param("email") String email,
            @Param("password") String password,
            @Param("updatedAt") LocalDateTime updatedAt
    );
}
