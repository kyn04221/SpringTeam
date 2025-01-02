package com.busanit501.bootproject.dto;

import com.busanit501.bootproject.domain.RoomStatus;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MatchingRoomDTO {

    private int roomId;
    private int hostId;
    private String title;
    private String description;
    private int maxParticipants;
    private int currentParticipants;
    private RoomStatus status;
    private Timestamp createdAt;
}
