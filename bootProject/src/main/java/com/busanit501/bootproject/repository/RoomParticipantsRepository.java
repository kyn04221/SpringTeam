package com.busanit501.bootproject.repository;

import com.busanit501.bootproject.domain.RoomParticipants;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomParticipantsRepository extends JpaRepository<RoomParticipants, Integer> {
}
