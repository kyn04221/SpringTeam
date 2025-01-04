package com.busanit501.bootproject.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pets")
public class Pets extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int petId; // 반려동물 ID

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User owner; // 반려동물의 주인 (User)

    @Column(nullable = false)
    private String name; // 반려동물 이름

    @Column(nullable = false)
    private String type; // 반려동물 종류 (예: 개, 고양이)

    private int age; // 반려동물 나이

    @Enumerated(EnumType.STRING)
    private Gender gender; // 반려동물 성별

    private String personality; // 반려동물 성격

    private Float weight; // 반려동물 몸무게

    private String profilePicture; // 반려동물 프로필 사진 URL

}