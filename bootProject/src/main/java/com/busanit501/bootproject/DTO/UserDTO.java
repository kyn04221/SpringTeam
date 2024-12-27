package com.busanit501.bootproject.DTO;

import com.busanit501.bootproject.domain.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;



@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private int userId; // 사용자 ID
    private String email; // 사용자 이메일
    private String name; // 사용자 이름
    private Integer age; // 사용자 나이
    private Gender gender; // 성별
    private String address; // 주소
    private String profilePicture; // 프로필 사진 URL
    private String phoneNumber; // 전화번호
    private boolean isVerified; // 계정 인증 여부
    private LocalDateTime createdAt; // 계정 생성 시간
    private LocalDateTime updatedAt; // 계정 수정 시간
}
