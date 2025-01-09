//package com.busanit501.bootproject.controller;
//
//
//import com.busanit501.bootproject.domain.MatchingRoom;
//import com.busanit501.bootproject.dto.CalendarDTO;
//import com.busanit501.bootproject.dto.MatchingRoomDTO;
//import com.busanit501.bootproject.dto.UserDTO;
//import com.busanit501.bootproject.service.CalendarService;
//import com.busanit501.bootproject.service.MatchingRoomService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import java.time.ZoneId;
//import java.time.ZonedDateTime;
//import java.util.List;
//
//@org.springframework.web.bind.annotation.RestController
//@RequestMapping("/api")
//@Log4j2
//@RequiredArgsConstructor
//public class RestController {
//
//    private final CalendarService calendarService;
//    private final MatchingRoomService matchingRoomService;
//
//    @GetMapping("/calendar/events")
//    public List<CalendarDTO> getCalendarEvents(UserDTO userDTO) {
//
////        Long userId = userDTO.getUserId(); // 로그인된 사용자의 ID 찾기
//        Long userId = 1L; // 더미데이터로 테스트중
//        List<CalendarDTO> calendars = calendarService.getUserCalendars(userId);
//
//
//        calendars.forEach(calendarDTO -> {
//            ZonedDateTime zonedDateTime = calendarDTO.getWalkDate().atStartOfDay(ZoneId.of("UTC"));
//            String isoDate = zonedDateTime.toInstant().toString();
//            calendarDTO.setWalkDateIso(isoDate);  // UTC로 변환된 날짜를 DTO에 저장
//        });
//
//        return calendars;
//    }
//
//    @GetMapping("/matching-room/{roomId}")
//    public MatchingRoomDTO getMatchingRoom(@PathVariable Long roomId) {
//        return matchingRoomService.findById(roomId);
//    }
//
//
//}
//

package com.busanit501.bootproject.controller;


import com.busanit501.bootproject.domain.MatchingRoom;
import com.busanit501.bootproject.dto.CalendarDTO;
import com.busanit501.bootproject.dto.MatchingRoomDTO;
import com.busanit501.bootproject.dto.UserDTO;
import com.busanit501.bootproject.service.CalendarService;
import com.busanit501.bootproject.service.MatchingRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/schedule")
@Log4j2
@RequiredArgsConstructor
public class RestController {

    private final CalendarService calendarService;
    private final MatchingRoomService matchingRoomService;

    //방법1
//    @GetMapping("/calendar/events")
//    public List<CalendarDTO> fetchEventDetails(@RequestParam UserDTO userDTO) {
//
////        Long userId = userDTO.getUserId(); // 로그인된 사용자의 ID 찾기
//        Long userId = 2L; // 더미데이터로 테스트중
//        List<CalendarDTO> calendarList = calendarService.getUserCalendars(userId);
//
//
//        calendarList.forEach(calendarDTO -> {
//            ZonedDateTime zonedDateTime = calendarDTO.getWalkDate().atStartOfDay(ZoneId.of("UTC"));
//            String isoDate = zonedDateTime.toInstant().toString();
//            calendarDTO.setWalkDateIso(isoDate);  // UTC로 변환된 날짜를 DTO에 저장
//        });
//
//        return calendarList;
//    }

    //방법2
    @GetMapping("/{userId}")
    public List<CalendarDTO> allschedule(@PathVariable("userId") Long userId,Model model) {

//        Long userId = userDTO.getUserId(); // 로그인된 사용자의 ID 찾기
        userId = 2L; // 더미데이터로 테스트

        List<CalendarDTO> calendarList = calendarService.getUserCalendars(userId);


        calendarList.forEach(calendarDTO -> {
            ZonedDateTime zonedDateTime = calendarDTO.getWalkDate().atStartOfDay(ZoneId.of("UTC"));
            String isoDate = zonedDateTime.toInstant().toString();
            calendarDTO.setWalkDateIso(isoDate);  // UTC로 변환된 날짜를 DTO에 저장
        });
        model.addAttribute("userid", userId);
        return calendarList;
    }

//    //방법3
//    @GetMapping("/{userId}")
//    public void fetchEventDetails(@PathVariable Long userId, Model model) {
//
////        Long userId = userDTO.getUserId(); // 로그인된 사용자의 ID 찾기
////        Long userId = 2L; // 더미데이터로 테스트중
//        List<CalendarDTO> calendarList = calendarService.getUserCalendars(userId);
//
//
//        calendarList.forEach(calendarDTO -> {
//            ZonedDateTime zonedDateTime = calendarDTO.getWalkDate().atStartOfDay(ZoneId.of("UTC"));
//            String isoDate = zonedDateTime.toInstant().toString();
//            calendarDTO.setWalkDateIso(isoDate);  // UTC로 변환된 날짜를 DTO에 저장
//        });
//
//        model.addAttribute("calendarList", calendarList);
//
//    }


    @GetMapping("/event/{scheduleId}")
    public void getEventDetail(@PathVariable("scheduleId") Long scheduleId, Model model) {
        // scheduleId를 이용해 이벤트 상세 정보를 가져오는 로직
//        CalendarDTO eventDetail = calendarService.getEventDetailByScheduleId(scheduleId);
//        return eventDetail;
        CalendarDTO eventDetail = calendarService.getEventDetailByScheduleId(scheduleId);
        model.addAttribute("eventDetail", eventDetail); // 상세 정보 추가
    }

    @GetMapping("/matching-room/{roomId}")
    public MatchingRoomDTO getMatchingRoom(@PathVariable Long roomId) {
        return matchingRoomService.findByRoomId(roomId);
    }


}

