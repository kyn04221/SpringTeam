package com.busanit501.bootproject.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ParticipantDTO {

    @NotBlank(message = "참가자 이름은 필수 입력 항목입니다.")
    private String userName;          // 화면에 표시할 참가자 이름

    private String profilePicturePath;  // 필요시
}
