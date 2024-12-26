package com.busanit501.bootproject.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsersDTO {

    private Long userid;

    @NotEmpty
    private String email;// 아이디 (이메일)

    @NotEmpty
    private String password;//비밀번호

    @NotEmpty
    private String name;// 이름

    @NotEmpty
    private Integer age; // 나이

    @NotEmpty
    private boolean gender; // 성별

    @NotEmpty
    private String address; // 주소

    private String profilePicture; // 프로필 사진 URL

    @NotEmpty
    private String phoneNumber;

    private boolean isVerified; // 계정 인증 여부
}
