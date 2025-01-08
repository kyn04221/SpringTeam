package com.busanit501.bootproject.service;

import com.busanit501.bootproject.dto.MatchingRoomDTO;

public interface MatchingRoomService {

    MatchingRoomDTO findById(Long roomId);
}