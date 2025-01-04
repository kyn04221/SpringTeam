package com.busanit501.bootproject.repository;

import com.busanit501.bootproject.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    @Query(value = "SELECT a.* " +
            "FROM message a " +
            "WHERE a.content like concat('%', :messageKeyword, '%') " +
            "AND a.chat_room_id = :roomId " +
            "ORDER BY a.sent_at DESC "
            ,nativeQuery = true)
    List<Message> searchMessageByMatchingRoomId(String messageKeyword,int roomId);
}
