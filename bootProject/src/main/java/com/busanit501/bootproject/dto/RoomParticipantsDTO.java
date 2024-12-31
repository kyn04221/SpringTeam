package com.busanit501.bootproject.dto;

import com.busanit501.bootproject.domain.RoomParticipantsStatus;

import java.sql.Timestamp;

public class RoomParticipantsDTO {
    private int roomParticipantsId;
    private int chatRoomId;
    private int senderId;
    private RoomParticipantsStatus status;
    private Timestamp createdAt;
}
