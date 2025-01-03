package com.busanit501.bootproject.CalendarRepository;


import com.busanit501.bootproject.domain.Calendar;
import com.busanit501.bootproject.domain.Gender;
import com.busanit501.bootproject.domain.ScheduleStatus;
import com.busanit501.bootproject.domain.User;
import com.busanit501.bootproject.repository.CalendarRepository;
import com.busanit501.bootproject.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


//@DataJpaTest
@Transactional
@Log4j2
@SpringBootTest
public class CalendarRepositoryTest {

    @Autowired
    private CalendarRepository calendarRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testAddCalendarEvent() {
        // 데이터베이스에 저장된 첫 번째 사용자 가져오기 (기존 사용자 사용)
        User testUser = userRepository.findAll().stream().findFirst().orElseThrow(() -> new RuntimeException("No user found"));

        // 일정 추가
        Calendar calendar = Calendar.builder()
                .schedulename("Morning Walk")
                .walkDate(LocalDate.of(2025, 1, 5))
                .walkTime(LocalTime.of(7, 30))
                .user(testUser) // 데이터베이스에서 가져온 사용자 설정
                .status(ScheduleStatus.SCHEDULED)
                .build();

        // 일정 저장
        Calendar savedCalendar = calendarRepository.save(calendar);

        // 저장된 일정 확인
        Optional<Calendar> foundCalendar = calendarRepository.findById(savedCalendar.getScheduleId());
        log.info("Saved calendar should be found.",foundCalendar.isPresent());
        assertEquals("Morning Walk", foundCalendar.get().getSchedulename(), "Event name should match.");
        assertEquals(ScheduleStatus.SCHEDULED, foundCalendar.get().getStatus(), "Event status should be scheduled.");
    }

}
