package com.busanit501.bootproject.controller;

import com.busanit501.bootproject.domain.User;
import com.busanit501.bootproject.dto.CalendarDTO;
import com.busanit501.bootproject.dto.UserDTO;
import com.busanit501.bootproject.service.CalendarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@RequestMapping("/calendar")
@Log4j2
@RequiredArgsConstructor
public class CalendarController {

        private final CalendarService calendarService;

        @GetMapping
        // @AuthenticationPrincipal 적용전
        //더미테스트 끝나면 사용
        public String calender(Model model, UserDTO user) {
//        public String calender(Model model) {
                //더미테스트 끝나면 사용
                List<CalendarDTO> schedules = calendarService.getUserSchedules(user.getUserId());
                model.addAttribute("schedules", schedules);
                return "calendar"; // calendar.html로 이동
//                Long userid = 1L;
//                List<CalendarDTO> schedules = calendarService.getUserSchedules(userid);
//                model.addAttribute("schedules", schedules);
//                return "calendar"; // calendar.html로 이동
        }
}






