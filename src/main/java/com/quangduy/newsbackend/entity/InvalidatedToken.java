package com.quangduy.newsbackend.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "invalidated_token")
public class InvalidatedToken {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    String id;

    @Column(name = "expiryTime")
    Date expiryTime;
}
