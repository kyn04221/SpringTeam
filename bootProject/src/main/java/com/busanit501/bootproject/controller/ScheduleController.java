package com.busanit501.bootproject.controller;

import com.busanit501.bootproject.dto.CalendarDTO;
import com.busanit501.bootproject.service.CalendarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    //방법1
    @GetMapping("/{userId}")
    public ResponseEntity<List<CalendarDTO>> getUserSchedules(@PathVariable("userId") Long userId) {
        List<CalendarDTO> schedules = calendarService.getUserSchedules(userId);

        log.info("Schedules: {}", schedules);

        schedules.forEach(calendarDTO -> {
            // walkDate와 walkTime을 결합하여 LocalDateTime 생성
            LocalDateTime localDateTime = calendarDTO.getWalkDate().atTime(calendarDTO.getWalkTime());
            String isoDate = localDateTime.toString();  // LocalDateTime은 자동으로 ISO 형식으로 변환

            calendarDTO.setWalkDateIso(isoDate);
        });

        return ResponseEntity.ok(schedules);
    }
//
//    // 일정 추가
//    @PostMapping("/add")
//    public ResponseEntity<String> addSchedule(@RequestBody CalendarDTO calendarDTO) {
//        try {
//            scheduleService.addSchedule(scheduleDTO);
//            return ResponseEntity.ok("일정 추가 완료");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("일정 추가 실패");
//        }
//    }
//
//    // 일정 삭제
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<String> deleteSchedule(@PathVariable Long id) {
//        try {
//            scheduleService.deleteSchedule(id);
//            return ResponseEntity.ok("일정 삭제 완료");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("일정 삭제 실패");
//        }
//    }


}
