package com.busanit501.bootproject.controller;

import com.busanit501.bootproject.domain.Calendar;
import com.busanit501.bootproject.dto.CalendarDTO;
import com.busanit501.bootproject.service.CalendarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@RestController
@RequestMapping("/schedule")
@Log4j2
@RequiredArgsConstructor
public class ScheduleController {

    private final CalendarService calendarService;


    @GetMapping("/{userId}")
    public ResponseEntity<List<CalendarDTO>> getUserSchedules(@PathVariable("userId") Long userId) {
        List<CalendarDTO> schedules = calendarService.getUserSchedules(userId);

        log.info("Schedules: {}", schedules);

        schedules.forEach(calendarDTO -> {

            LocalDateTime localDateTime = calendarDTO.getWalkDate().atTime(calendarDTO.getWalkTime());
            String isoDate = localDateTime.toString();  // LocalDateTime은 자동으로 ISO 형식으로 변환

            calendarDTO.setWalkDateIso(isoDate);
        });

        return ResponseEntity.ok(schedules);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addSchedule(@RequestBody CalendarDTO calendarDTO) {
            try {
                // 서비스에서 일정 추가
                Calendar calendar = calendarService.addSchedule(calendarDTO);

                // 성공적으로 일정이 추가되었다는 응답
                return ResponseEntity.ok("일정이 저장 되었습니다");
            } catch (Exception e) {
                // 실패 시 에러 메시지 응답
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("일정 추가 실패");
            }


    }


}
