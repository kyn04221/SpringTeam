package com.busanit501.bootproject.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log4j2
//@RequestMapping("/calender")
@RequiredArgsConstructor
public class CalendarController {

        @GetMapping("/calendar")
        public String calendar() {
                return "calendar";
        }

        @GetMapping("/calendar2")
        public void calendar2() {

        }
}
