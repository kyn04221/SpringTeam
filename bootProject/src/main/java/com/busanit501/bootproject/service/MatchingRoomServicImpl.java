package com.busanit501.bootproject.service;


import com.busanit501.bootproject.domain.Calendar;
import com.busanit501.bootproject.domain.MatchingRoom;
import com.busanit501.bootproject.dto.MatchingRoomDTO;
import com.busanit501.bootproject.repository.CalendarRepository;
import com.busanit501.bootproject.repository.MatchingRoomRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class MatchingRoomServicImpl implements MatchingRoomService {


    private MatchingRoomRepository matchingRoomRepository;
    private CalendarRepository calendarRepository;

    @Override
    public MatchingRoomDTO findByRoomId(Long roomId) {
        // MatchingRoom을 찾을 때 Optional을 사용하여 예외 처리를 할 수 있음
        Optional<MatchingRoom> roomid = matchingRoomRepository.findById(roomId);
        MatchingRoom matchingRoom = roomid.orElseThrow();
        MatchingRoomDTO matchingRoomDTO = entityToDto(matchingRoom);

        return matchingRoomDTO;
    }

//매칭룸 >> 캘린더 저장 !!!
    public MatchingRoom createMatchingRoom(MatchingRoomDTO matchingRoomDTO) {
        // 매칭 룸 생성
        MatchingRoom matchingRoom = MatchingRoom.builder()
                .title(matchingRoomDTO.getTitle())
                .description(matchingRoomDTO.getDescription())
                .place(matchingRoomDTO.getPlace())
                .meetingDate(matchingRoomDTO.getMeetingDate())
                .meetingTime(matchingRoomDTO.getMeetingTime())// 예시: 상대방 사용자 정보
                .build();

        // 매칭 룸 저장
        MatchingRoom savedMatchingRoom = matchingRoomRepository.save(matchingRoom);

        // 매칭 룸 > 캘린더 저장 부분--------------
        Calendar calendar = savedMatchingRoom.createCalendarFromMatching();
        calendarRepository.save(calendar); // Calendar 저장

        return savedMatchingRoom;
    }




}
