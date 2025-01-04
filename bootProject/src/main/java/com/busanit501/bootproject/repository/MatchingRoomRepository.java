package com.busanit501.bootproject.repository;

import com.busanit501.bootproject.domain.MatchingRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;


public interface MatchingRoomRepository extends JpaRepository<MatchingRoom, Integer> {
    //매칭방 조회(로그인 회원이 포함된 매칭방)
    @Query(value = "SELECT DISTINCT a.* " +
            "FROM matching_room a LEFT JOIN message b ON a.room_id = b.chat_room_id " +
            "LEFT JOIN room_participants rp ON a.room_id = rp.chat_room_id " +
            "WHERE a.title like concat('%', :keyword, '%') " +
            "AND rp.sender_id = :userId " +
            "ORDER BY IFNULL(b.sent_at, a.created_at) DESC",
            nativeQuery = true)
    List<MatchingRoom> searchAllMatchingRoom(String keyword,int userId);

    //열린 매칭방만 조회(최근 채팅 정렬)
    @Query(value = "SELECT DISTINCT a.* " +
            "FROM matching_room a LEFT JOIN message b ON a.room_id = b.chat_room_id  " +
            "WHERE a.title like concat('%', :keyword, '%') " +
            "AND a.status = 'Open' " +
            "ORDER BY IFNULL(b.sent_at, a.created_at) DESC",
            nativeQuery = true)
    List<MatchingRoom> findMatchingRoomByOnOff(String keyword);

    Optional<MatchingRoom> findByTitle(String title);

    // 예시: 특정 날짜 이후의 매칭 방을 조회
    Page<MatchingRoom> findByMeetingDateAfter(LocalDate date, Pageable pageable);

}
