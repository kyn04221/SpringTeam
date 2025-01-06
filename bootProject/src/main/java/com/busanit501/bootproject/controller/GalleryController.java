package com.busanit501.bootproject.controller;

import com.busanit501.bootproject.dto.GalleryListAllDTO;
import com.busanit501.bootproject.dto.PageRequestDTO;
import com.busanit501.bootproject.dto.PageResponseDTO;
import com.busanit501.bootproject.service.GalleryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/gallery")
@RequiredArgsConstructor
public class GalleryController {
    // 물리 저장소 경로를 불러오기.
    @Value("${com.busanit501.upload.path}")
    private String uploadPath;

    private final GalleryService galleryService;

    @GetMapping("/list")
    public void list(@AuthenticationPrincipal UserDetails user, PageRequestDTO pageRequestDTO, Model model ) {
        PageResponseDTO<GalleryListAllDTO> responseDTO = galleryService.listWithAll(pageRequestDTO);
//        PageResponseDTO<GalleryDTO> responseDTO = galleryService.list(pageRequestDTO);

        // user 정보를 화면에 전달하기.
        model.addAttribute("user", user);
        model.addAttribute("responseDTO", responseDTO);

    }

}
