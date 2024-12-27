package com.busanit501.bootproject.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User extends BaseEntity {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int userId; // 사용자 ID

        @Column(nullable = false, unique = true)
        private String email; // 사용자 이메일 (로그인 아이디로 사용)

        @Column(nullable = false)
        private String password; // 암호화된 비밀번호

        @Column(nullable = false)
        private String name; // 사용자 이름

        private Integer age; // 사용자 나이

        @Enumerated(EnumType.STRING)
        private Gender gender; // 성별 (Male/Female)

        private String address; // 주소 (동까지)

        private String profilePicture; // 프로필 사진 URL

        private String phoneNumber; // 전화번호

        @Column(name = "is_verified", columnDefinition = "BOOLEAN DEFAULT FALSE")
        private boolean isVerified; // 계정 인증 여부

        @Column(nullable = false)
        private LocalDateTime createdAt; // 계정 생성 시간

        private LocalDateTime updatedAt; // 계정 수정 시간
}
