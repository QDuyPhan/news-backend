package com.quangduy.newsbackend.entity;

import java.util.Set;

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

//    @ManyToMany
//    Set<Role> roles;

    @Embedded
    Timestamps timestamps;
}
