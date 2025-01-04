package com.busanit501.bootproject.dto;

import com.busanit501.bootproject.domain.RoomStatus;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class MatchingRoomNotReadChatCountDTO {
    private int roomId;
    private int hostId;
    private String title;
    private String description;
    private int maxParticipants;
    private int currentParticipants;
    private RoomStatus status;
    private Timestamp createdAt;

    private int notReadCount;
}
