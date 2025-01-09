package com.busanit501.bootproject.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "matching_rooms")
public class MatchingRoom extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "host_id", nullable = false)
    private User host;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "calendar_id", nullable = true) // Calendar와 연결
    private Calendar calendar;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private String place;

    @Column(nullable = false)
    private LocalDate meetingDate;

    @Column(nullable = false)
    private LocalTime meetingTime;


//
//    //매칭룸 > 캘린더 저장 !
//    public Calendar createCalendarFromMatching() {
//        MatchingRoom matchingRoom = MatchingRoom.builder()
//                .host(this.host)
//                .user(this.user)
//                .title(this.title)
//                .place(this.place)
//                .meetingDate(this.meetingDate)
//                .meetingTime(this.meetingTime)
//                .calendar(null) // MatchingRoom에 Calendar를 설정
//                .build();
//
//        Calendar calendar= Calendar.builder()
//                .matchingRooms(List.of(matchingRoom))
//                .schedulename(this.title) // 매칭 제목을 일정명으로
//                .walkDate(this.meetingDate) // 매칭 날짜를 산책 날짜로
//                .walkTime(this.meetingTime) // 매칭 시간을 산책 시간으로
//                .walkPlace(this.place) // 매칭 장소를 산책 장소로
//                .matchingId(this.roomId) // 매칭 ID를 저장
////                .user(this.host) // 매칭의 방장은 사용자로 설정
//                .status(ScheduleStatus.SCHEDULED) // 상태를 '예정'으로 설정
//                .build();
//
//
//        matchingRoom.addCalendar(calendar);
////        calendar.addMatchingRoom(matchingRoom); // Calendar에 matchingRoom을 추가
//
//        return calendar;
//    }
//
//    public void addCalendar(Calendar calendar) {
//        this.calendar = calendar;
//    }
//
//    public void addMatchingRoom(MatchingRoom matchingRoom) {
//        if (this.matchingRooms == null) {
//            this.matchingRooms = new ArrayList<>();
//        }
//        this.matchingRooms.add(matchingRoom);
//    }

//
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "calendar_id")
//    private Calendar calendar;
//

//    public User getOtherUser(User currentUser) {
//        if (host.equals(currentUser)) {
//            // 현재 사용자가 host인 경우, user 정보를 반환
//            return user;
//        } else if (user.equals(currentUser)) {
//            // 현재 사용자가 user인 경우, host 정보를 반환
//            return host;
//        }
//        return null;  // 매칭룸에 속한 사용자가 아닌 경우
//    }


}
