package com.busanit501.bootproject.domain;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "calendar")
public class Calendar extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer scheduleId;  // 일정 ID (자동 증가)

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;  // 사용자와의 관계 (외래 키)

    @Column(name = "walk_date", nullable = false)
    private LocalDate walkDate;  // 산책 날짜

    @Column(name = "walk_time", nullable = false)
    private LocalTime walkTime;  // 산책 시간

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ScheduleStatus status = ScheduleStatus.SCHEDULED;  // 산책 상태 (예정, 완료, 취소)

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;  // 일정 생성 시간

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;  // 일정 수정 시간

}


