package com.busanit501.bootproject.dto;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsersDTO {
    private String email;
    private String password;
    private String name;
    private LocalDate birth;
    private String gender;
    private String address;
    private String phoneNumber;
    private String zipcode;
    private String detailedAddress;

    // 기본 프로필 사진 URL은 여기에서 설정할 수 있습니다
    private String profilePicture = "/images/default-profile.jpg";
}