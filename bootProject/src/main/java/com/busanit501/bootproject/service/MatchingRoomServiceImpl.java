package com.busanit501.bootproject.service;

import com.busanit501.bootproject.domain.MatchingRoom;
import com.busanit501.bootproject.domain.RoomParticipants;
import com.busanit501.bootproject.domain.RoomStatus;
import com.busanit501.bootproject.domain.User;
import com.busanit501.bootproject.dto.MatchingRoomDTO;
import com.busanit501.bootproject.repository.MatchingRoomRepository;
import com.busanit501.bootproject.repository.UserRepostiory;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class MatchingRoomServiceImpl implements MatchingRoomService {
    @Autowired
    private MatchingRoomRepository matchingRoomRepository;
    @Autowired
    private UserRepostiory userRepostiory;
    private RoomParticipants roomParticipants;
    private final ModelMapper modelMapper;

    @Override
    public int addMatchingRoom(MatchingRoomDTO matchingRoomDTO) {
        // DTO -> MatchingRoom 변환
        MatchingRoom matchingRoom = modelMapper.map(matchingRoomDTO, MatchingRoom.class);

        // hostId로 User 엔티티 조회
        User host = userRepostiory.findById(matchingRoomDTO.getHostId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid hostId: " + matchingRoomDTO.getHostId()));

        // MatchingRoom의 host 필드 설정
        matchingRoom.setHost(host);

        // 매칭룸 저장
        MatchingRoom savedRoom = matchingRoomRepository.save(matchingRoom);
        return savedRoom.getRoomId();
    }

    @Override
    public void updateMatchingRoom(MatchingRoomDTO matchingRoomDTO) {
        Optional<MatchingRoom> result = matchingRoomRepository.findById(matchingRoomDTO.getRoomId());
        MatchingRoom matchingRoom = result.orElseThrow();
        matchingRoom.MatchingRoomUpdate(matchingRoomDTO.getTitle(),
                matchingRoomDTO.getDescription(),
                matchingRoomDTO.getMaxParticipants(),
                matchingRoomDTO.getCurrentParticipants(),
                matchingRoomDTO.getStatus());
        matchingRoomRepository.save(matchingRoom);
    }

    @Override
    public void deleteMatchingRoom(int matchingRoomID) {
        matchingRoomRepository.deleteById(matchingRoomID);
    }

    @Override
    public List<MatchingRoomDTO> searchAllMatchingRoom(String keyword) {
        // 키워드로 매칭룸 검색
        List<MatchingRoom> matchingRooms = matchingRoomRepository.searchAllMatchingRoom(keyword);

        // 검색 결과를 DTO로 변환
        List<MatchingRoomDTO> dtoList = new ArrayList<>();
        for (MatchingRoom matchingRoom : matchingRooms) {
            MatchingRoomDTO dto = MatchingRoomDTO.builder()
                    .roomId(matchingRoom.getRoomId())
                    .hostId(matchingRoom.getHost().getUserId()) // User 엔티티에서 hostId 추출
                    .title(matchingRoom.getTitle())
                    .description(matchingRoom.getDescription())
                    .maxParticipants(matchingRoom.getMaxParticipants())
                    .currentParticipants(matchingRoom.getCurrentParticipants())
                    .status(matchingRoom.getStatus())
                    .createdAt(matchingRoom.getCreatedAt())
                    .build();
            dtoList.add(dto);
        }

        return dtoList;
    }

}
