package com.busanit501.bootproject.repository;

import com.busanit501.bootproject.domain.MatchingRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MatchingRoomRepository extends JpaRepository<MatchingRoom, Integer> {
    //매칭방 이름으로 조회(최근 채팅 정렬)
    @Query(value = "SELECT DISTINCT a.* " +
            "FROM matching_room a LEFT JOIN message b ON a.room_id = b.chat_room_id  " +
            "WHERE a.title like concat('%', :keyword, '%') " +
            "ORDER BY IFNULL(b.sent_at, a.created_at) DESC",
            nativeQuery = true)
    List<MatchingRoom> searchAllMatchingRoom(String keyword);


    //열린 매칭방만 조회(최근 채팅 정렬)
    @Query(value = "SELECT DISTINCT a.* " +
            "FROM matching_room a LEFT JOIN message b ON a.room_id = b.chat_room_id  " +
            "WHERE a.title like concat('%', :keyword, '%') " +
            "AND a.status = 'Open'" +
            "ORDER BY IFNULL(b.sent_at, a.created_at) DESC",
            nativeQuery = true)
    List<MatchingRoom> findMatchingRoomByOnOff(String keyword);
}
