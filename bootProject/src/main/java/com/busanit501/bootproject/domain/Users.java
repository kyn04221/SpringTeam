package com.busanit501.bootproject.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Users extends BaseEntity{

    @Id
    @Column(length = 50, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId; // 사용자 Number

    @Column(length = 100, nullable = false)
    private String email; // 사용자 이메일

    @Column(length = 50, nullable = false)
    private String password; // 암호화된 비밀번호

    @Column(length = 50, nullable = false)
    private String name; // 사용자 이름

    @Column(length = 10, nullable = false)
    private Integer age; // 사용자 나이 (null 가능)

    @Column(nullable = false)
    private boolean gender; // 성별

    @Column(length = 200, nullable = false)
    private String address; // 주소

    @Column(length = 300, nullable = false)
    private String profilePicture; // 프로필 사진 URL

    @Column(length = 100, nullable = false)
    private String phoneNumber; // 전화번호

    @Column(nullable = false)
    private boolean isVerified; // 계정 인증 여부

}
