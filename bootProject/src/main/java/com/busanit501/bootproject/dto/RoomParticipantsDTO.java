package com.busanit501.bootproject.dto;

import com.busanit501.bootproject.domain.RoomParticipantsStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomParticipantsDTO {
    private int roomParticipantsId;
    private int chatRoomId;
    private int senderId;
    private RoomParticipantsStatus status;
    private Timestamp createdAt;
}
