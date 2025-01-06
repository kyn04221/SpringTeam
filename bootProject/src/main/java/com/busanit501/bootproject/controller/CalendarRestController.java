package com.busanit501.bootproject.controller;


import com.busanit501.bootproject.dto.CalendarDTO;
import com.busanit501.bootproject.service.CalendarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
@Log4j2
@RequiredArgsConstructor
public class CalendarRestController {

    private final CalendarService calendarService;

    @GetMapping("/calendar/events")
    public List<CalendarDTO> getCalendarEvents() {
        List<CalendarDTO> calendars = calendarService.getAllCalendars();

        // 각 이벤트의 날짜를 UTC로 변환하여 ISO 8601 형식으로 설정
        calendars.forEach(calendarDTO -> {
            ZonedDateTime zonedDateTime = calendarDTO.getWalkDate().atStartOfDay(ZoneId.of("UTC"));
            String isoDate = zonedDateTime.toInstant().toString();
            calendarDTO.setWalkDateIso(isoDate);  // UTC로 변환된 날짜를 DTO에 저장
        });

        return calendars;
//-----------------------
//        return calendarService.getAllCalendars();
        //------------------------
    }
}
