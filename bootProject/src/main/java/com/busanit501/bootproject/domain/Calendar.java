package com.busanit501.bootproject.domain;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "calendar")
public class Calendar extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;  // 일정 ID (자동 증가)

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;  // 사용자와의 관계 (외래 키)

    @Column(name = "schedule_name", nullable = false)
    private String schedulename;

    @Column(name = "walk_date")
    private LocalDate walkDate;  // 산책 날짜

    @Column(name = "walk_time")
    private LocalTime walkTime;  // 산책 시간

    @Column(name = "walk_place", nullable = false)
    private String walkPlace;

    @Enumerated(EnumType.STRING)
    @Column
//            (nullable = false)
    @Builder.Default
    private ScheduleStatus status = ScheduleStatus.SCHEDULED;  // 산책 상태 (예정, 완료, 취소)



    @Column(name = "matching")
    private Boolean matching;
    @Column(name = "schedul_start")
    private LocalDate schedulStart;
    @Column(name = "schedul_end")
    private LocalDate schedulEnd;

    public void changeStatus(ScheduleStatus status) {

        this.status = status;
    }


}


