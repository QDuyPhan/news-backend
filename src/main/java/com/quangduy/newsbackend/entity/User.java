package com.quangduy.newsbackend.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    @Column(name = "name")
    String name;
    @Column(name = "username", unique = true)
    String username;
    @Column(name = "email", unique = true)
    String email;
    @Column(name = "password")
    String password;
    @Column(name = "created_at")
    LocalDateTime created_at;
    @Column(name = "updated_at")
    LocalDateTime updated_at;
    //    @ManyToMany
    //    Set<Role> roles;
}
