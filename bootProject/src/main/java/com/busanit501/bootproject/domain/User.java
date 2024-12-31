package com.busanit501.bootproject.domain;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

// User Entity
@Entity
@Table(name = "Users")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String address;

    private String profilePicture;

    private String phoneNumber;

    @Builder.Default
    @Column(nullable = false)
    private boolean isVerified = false;

    @Column(updatable = false)
    private Timestamp createdAt;
}
