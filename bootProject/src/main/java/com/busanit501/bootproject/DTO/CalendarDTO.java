package com.busanit501.bootproject.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CalendarDTO {

    private Integer scheduleId;  // 일정 ID
    private Integer userId;      // 사용자 ID
    private LocalDate walkDate;  // 산책 날짜
    private LocalTime walkTime;  // 산책 시간
    private String status;       // 산책 상태
    private LocalDateTime createdAt;  // 일정 생성 시간
    private LocalDateTime updatedAt;  // 일정 수정 시간

}
