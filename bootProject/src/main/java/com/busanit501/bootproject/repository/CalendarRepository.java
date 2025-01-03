package com.busanit501.bootproject.repository;

import com.busanit501.bootproject.domain.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CalendarRepository extends JpaRepository<Calendar, Long> {

//
//    @Query("select b from Calendar b where b.schedulename like concat('%',:keyword,'%')")
//    Page<Calendar> findByKeyword(String keyword, Pageable pageable);
//
//    @Query(value = "select now()" , nativeQuery = true)
//    String now();
//

}
