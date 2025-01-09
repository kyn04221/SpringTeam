//(모달) 상세 정보---------------------------
function displayEventDetails(eventDetails) {
    const modalTitle = document.getElementById('modalTitle');
    const modalDate = document.getElementById('modalDate');
    const modalTime = document.getElementById('modalTime');
    const modalPlace = document.getElementById('modalPlace');

    modalTitle.innerText = eventDetails.schedulename;  // 일정 제목
    modalDate.innerText = eventDetails.walkDateIso;    // 일정 날짜
    modalTime.innerText = eventDetails.walkTime || 'N/A';  // 시간 정보
    modalPlace.innerText = eventDetails.place || 'N/A';    // 장소 정보

    // 모달 띄우기
    document.getElementById('eventModal').style.display = 'flex';
}

// 서버에서 이벤트 상세 정보를 비동기로 받아오는 함수
async function getEventDetails(eventId) {
    try {
        const response = await fetch(`/schedule/event/${eventId}`); // 이벤트 상세 정보 요청

        if (!response.ok) {
            throw new Error("일정 상세 정보를 불러오는 데 실패했습니다.");
        }

        const eventDetails = await response.json();  // 응답을 JSON 형식으로 변환

        // 모달에 일정 정보 표시
        displayEventDetails(eventDetails);
    } catch (error) {
        console.error("Error fetching event details:", error);
    }
}

//-------------------------------------------------------


//달력에 전체 일정 보이게 하기----------------------------------
// async function allschedule(userId) {
//     try {
//         const response = await fetch(`/calendar/${userId}`);
//         return response.data
//         // API 응답 상태가 OK인지 확인
//         if (!response.ok) {
//             throw new Error("캘린더 데이터를 불러오는 데 실패했습니다.");
//         }
//
//         // JSON 응답을 파싱
//         const events = await response.json();
//
//         // 캘린더 데이터를 HTML에 표시
//         displayCalendarEvents(events);
//     } catch (error) {
//         console.error("Error fetching calendar events:", error);
//     }
// }

// async function allschedule(userId) {
//         const response = await fetch(`/calendar/${userId}`);
//         return response.data
// }

async function allschedule(userId) {
    try {
        const response = await fetch(`/schedule/${userId}`);  // URL에 userId를 포함하여 fetch 요청
        const data = await response.json();  // 서버 응답을 JSON 형식으로 변환하여 data에 할당
        console.log('정보확인하기',data);
        return data;  // data 객체 반환
    } catch (error) {
        console.error('일정 데이터 로딩 실패:', error);
    }
}

// 캘린더 이벤트를 화면에 표시하는 함수
function displayCalendarEvents(events) {
    const eventsContainer = document.getElementById("calendar-events");

    // 기존의 이벤트 목록을 비우고 새로운 이벤트를 추가
    eventsContainer.innerHTML = '';

    events.forEach(event => {
        const eventElement = document.createElement("div");
        eventElement.classList.add("event");

        const eventTitle = document.createElement("h3");
        eventTitle.textContent = event.schedulename; // 일정 이름
        eventElement.appendChild(eventTitle);

        const eventDate = document.createElement("p");
        eventDate.textContent = `날짜: ${event.walkDateIso}`; // ISO 포맷 날짜
        eventElement.appendChild(eventDate);

        const eventPlace = document.createElement("p");
        eventPlace.textContent = `장소: ${event.walkPlace || "N/A"}`; // 장소 (없으면 N/A)
        eventElement.appendChild(eventPlace);

        // 캘린더 이벤트를 HTML에 추가
        eventsContainer.appendChild(eventElement);
    });
}

// 특정 매칭룸을 가져오는 함수
async function fetchMatchingRoom(roomId) {
    try {
        const response = await fetch(`/matching-room/${roomId}`);

        if (!response.ok) {
            throw new Error("매칭룸 정보를 불러오는 데 실패했습니다.");
        }

        const matchingRoom = await response.json();

        // 매칭룸 정보 출력
        displayMatchingRoom(matchingRoom);
    } catch (error) {
        console.error("Error fetching matching room:", error);
    }
}

// 매칭룸 정보를 화면에 표시하는 함수
function displayMatchingRoom(matchingRoom) {
    const roomContainer = document.getElementById("matching-room");

    roomContainer.innerHTML = '';

    const roomTitle = document.createElement("h3");
    roomTitle.textContent = `매칭룸: ${matchingRoom.roomName}`;
    roomContainer.appendChild(roomTitle);

    const roomDetails = document.createElement("p");
    roomDetails.textContent = `상태: ${matchingRoom.status}`;
    roomContainer.appendChild(roomDetails);

    const roomDescription = document.createElement("p");
    roomDescription.textContent = `설명: ${matchingRoom.description}`;
    roomContainer.appendChild(roomDescription);
}

// DOMContentLoaded 이벤트가 발생하면 fetchCalendarEvents 함수 호출
document.addEventListener("DOMContentLoaded", function() {
    fetchCalendarEvents(); // 캘린더 이벤트 가져오기
});

// 매칭룸 ID를 입력받아 fetchMatchingRoom을 호출하는 예시
// 예시: roomId가 1인 매칭룸 정보를 가져오기
fetchMatchingRoom(1);
