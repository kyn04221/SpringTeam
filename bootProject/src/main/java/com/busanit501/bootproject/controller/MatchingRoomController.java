package com.busanit501.bootproject.controller;

import com.busanit501.bootproject.dto.MatchingRoomDTO;
import com.busanit501.bootproject.service.MatchingRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Log4j2
@RequestMapping("/matchingRoom")
@RequiredArgsConstructor
public class MatchingRoomController {
    private final MatchingRoomService matchingRoomService;
    // 매칭룸 리스트 조회
    @GetMapping("/roomList")
    public String roomList(@RequestParam(required = false, defaultValue = "") String keyword, Model model) {
        //유저 번호
        int userId = 1;

        // 키워드에 맞는 매칭룸 리스트를 검색
        List<MatchingRoomDTO> roomList = matchingRoomService.searchAllMatchingRoom(keyword,userId);

        // 검색된 매칭룸 리스트를 모델에 추가
        model.addAttribute("roomList", roomList);

        // 검색어가 있을 경우 검색어를 모델에 추가 (필요시 화면에 검색어를 표시하기 위해)
        model.addAttribute("keyword", keyword);

        // 매칭룸 리스트 페이지를 반환 (템플릿 파일 이름)
        return "matchingRoom/roomList";
    }
}
