// schedule.js
// 유저의 전체 일정 확인하기---------------------
async function getUserSchedules(userId) {
    const response = await fetch(`/schedule/${userId}`);
    const schedules = await response.json();
    // var eventDate = new Date(event.walkDateIso);
    return schedules.map(schedule => ({
        title: schedule.schedulename,
        start: new Date(schedule.walkDateIso),  // LocalDate를 JavaScript Date 객체로 변환
        extendedProps: {
            day: schedule.walkDate,  // 날짜 그대로 사용
            time: schedule.walkTime,
            place: schedule.walkPlace,
            // matching: schedule.matching
        }
    }));
}
// 일정 추가 모달을 표시하는 함수
function openScheduleModal(startDate, endDate) {

    document.getElementById('scheduleModal').style.display = 'block';

    // 모달에 선택한 날짜 범위 표시
    document.getElementById('scheduleName').value = '';
    document.getElementById('schedulePlace').value = '';

    // 폼 제출 시 일정 저장
    document.getElementById('scheduleForm').onsubmit = function(event) {
        event.preventDefault();

        // 선택한 날짜, 장소, 이름 가져오기
        var scheduleName = document.getElementById('scheduleName').value;
        var schedulePlace = document.getElementById('schedulePlace').value;

        // 서버로 비동기 요청 보내기 (예시로 JSON 포맷으로 요청)
        addScheduleToServer({
            scheduleName: scheduleName,
            schedulePlace: schedulePlace,
            scheduleStart: startDate,  // 선택한 시작 날짜
            scheduleEnd: endDate,      // 선택한 끝 날짜
            matching: false            // 매칭된 일정이 아니므로 false
        });

        // 모달 닫기
        document.getElementById('scheduleModal').style.display = 'none';
    };
}

// 서버에 일정 추가하는 함수
function addScheduleToServer(scheduleData) {
    console.log("서버에 전송될 일정 데이터:", scheduleData);
    fetch('/schedule/add', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(scheduleData),
    })
        .then(response => response.json())
        .then(data => {
            console.log("일정 추가 성공:", data);
            alert("일정이 추가되었습니다!");
        })
        .catch(error => {
            console.error("일정 추가 오류:", error);
            alert("일정 추가에 실패했습니다.");
        });
}




// 우클릭 메뉴 표시
function showContextMenu(event, date) {
    // 우클릭 메뉴
    let contextMenu = document.getElementById('contextMenu');
    let selectedDate = null;

    // if (event.button == 2) return;
    event.preventDefault();
    selectedDate = date;
    contextMenu.style.top = event.pageY + 'px';
    contextMenu.style.left = event.pageX + 'px';
    contextMenu.style.display = 'block';
}



// 일정 추가 서버에 요청 (POST)
async function addScheduleToDB(scheduleData) {
    try {
        const response = await fetch('/schedule/add', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(scheduleData),
        });
        if (response.ok) {
            alert('일정이 추가되었습니다!');
            calendar.refetchEvents();  // 일정을 갱신
        } else {
            alert('일정 추가 실패');
        }
    } catch (error) {
        console.error('일정 추가 오류:', error);
    }
}
//(모달) 상세 정보---------------------------
function displayEventDetails(eventDetails) {

    const modalTitle = document.getElementById('modalTitle');
    const modalDate = document.getElementById('modalDate');
    const modalTime = document.getElementById('modalTime');
    const modalPlace = document.getElementById('modalPlace');


    modalTitle.innerText = eventDetails.schedulename;// 일정 제목
    modalDate.innerText = formatDate(eventDetails.walkDate) || 'N/A';  // 일정 날짜 형식 수정
    modalTime.innerText = formatTime(eventDetails.walkTime) || 'N/A';  // 시간 정보
    modalPlace.innerText = eventDetails.place || 'N/A';    // 장소 정보

    // 모달 띄우기
    document.getElementById('scheduleModal').style.display = 'flex';
}

//---------------------------------------------
// 시간 형식 변환 함수
function formatTime(walkTime) {
    if (!walkTime) return 'N/A';
    // walkTime이 문자열인지 확인하고, 문자열이 아니면 강제로 문자열로 변환
    if (typeof walkTime !== 'string') {
        walkTime = String(walkTime);  // 문자열로 변환
    }
    const timeParts = walkTime.split(',');
    const hours = timeParts[0].padStart(2, '0');  // 시간 앞에 0 추가
    const minutes = timeParts[1].padStart(2, '0');  // 분 앞에 0 추가
    return `${hours}시 ${minutes}분`;
}

// 날짜 형식 변환 함수
function formatDate(date) {
    if (!(date instanceof Date) || isNaN(date)) return 'N/A';  // 유효한 날짜가 아닐 경우 'N/A'

    // 날짜를 "yyyy/mm/dd" 형식으로 변환
    const year = date.getFullYear();
    const month = (date.getMonth() + 1).toString().padStart(2, '0');  // 월을 두 자리로 맞춤
    const day = date.getDate().toString().padStart(2, '0');  // 일을 두 자리로 맞춤

    return `${year}년 ${month}월 ${day}일`;  // "yyyy-mm-dd" 형식으로 반환

}