package com.busanit501.bootproject.service;


import com.busanit501.bootproject.dto.CalendarDTO;
import com.busanit501.bootproject.domain.Calendar;
import com.busanit501.bootproject.repository.CalendarRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class CalendarServiceImpl implements CalendarService {

    private final CalendarRepository calendarepository;

    @Override
    public Long register(CalendarDTO calendarDTO) {
        Calendar calendar = dtoToEntity(calendarDTO);
        Long id = calendarepository.save(calendar).getScheduleId();
        return id;
    }

    @Override
    public CalendarDTO readOne(Long scheduleId) {
        Optional<Calendar> result = calendarepository.findById(scheduleId);
        Calendar calendar= result.orElseThrow();
        CalendarDTO dto = entityToDto(calendar);
        return dto;
    }

    @Override
    public void update(CalendarDTO calendarDTO) {

        Optional<Calendar> result = calendarepository.findById(calendarDTO.getScheduleId());
        Calendar calendar = result.orElseThrow();
        calendar.changeCalendar(calendarDTO.getSchedulename(),
                calendarDTO.getWalkDate(), calendarDTO.getWalkTime());

        calendarepository.save(calendar);
    }

    @Override
    public void delete(Long scheduleId) {
        calendarepository.deleteById(scheduleId);
    }

    @Override
    public List<CalendarDTO> getAllCalendars() {
        List<Calendar> calendar = calendarepository.findAll();

        List<CalendarDTO> calendarDTO = calendar.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());

        return calendarDTO;

    }
//
//
//    @Override
//    public PageResponseDTO<CalendarDTO> list(PageRequestDTO pageRequestDTO) {
//        String[] types = pageRequestDTO.getTypes();
//        String keyword = pageRequestDTO.getKeyword();
//        Pageable pageable = pageRequestDTO.getPageable("bno");
//
//        Page<Board> result = boardRepository.searchAll(types,keyword,pageable);
//        // list -> PageResponseDTO 타입으로 변경 필요.
//
//        // result.getContent() -> 페이징된 엔티티 클래스 목록
//        List<BoardDTO> dtoList = result.getContent().stream()
//                .map(board ->modelMapper.map(board, BoardDTO.class))
//                .collect(Collectors.toList());
//
//
//        return PageResponseDTO.<BoardDTO>withAll()
//                .pageRequestDTO(pageRequestDTO)
//                .dtoList(dtoList)
//                .total((int) result.getTotalElements())
//                .build();
//
//}     // list
//
//
//    @Override
//    public PageResponseDTO<BoardListAllDTO> listWithAll(PageRequestDTO pageRequestDTO) {
//
//
//        String[] types = pageRequestDTO.getTypes();
//        String keyword = pageRequestDTO.getKeyword();
//        Pageable pageable = pageRequestDTO.getPageable("bno");
//
//        // 수정1
//        Page<BoardListAllDTO> result = boardRepository.searchWithAll(types,keyword,pageable);
//
//        return PageResponseDTO.<BoardListAllDTO>withAll()
//                .pageRequestDTO(pageRequestDTO)
//                .dtoList(result.getContent())
//                .total((int) result.getTotalElements())
//                .build();
//    }
}
