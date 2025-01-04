// 일정 전체 목록 조회
async function getSchedules({ page, size }) {
    try {
        const response = await axios.get('/schedules', { params: { page, size } });
        return response.data; // 일정 목록 반환
    } catch (error) {
        console.error('Failed to fetch schedules:', error);
        throw error;
    }
}

// 일정 추가
async function addSchedule(schedule) {
    try {
        const response = await axios.post('/schedules', schedule);
        return response.data; // 추가된 일정 ID 반환
    } catch (error) {
        console.error('Failed to add schedule:', error);
        throw error;
    }
}

// 일정 조회 (단일 일정)
async function getScheduleById(scheduleId) {
    try {
        const response = await axios.get(`/schedules/${scheduleId}`);
        return response.data; // 일정 데이터 반환
    } catch (error) {
        console.error('Failed to fetch schedule:', error);
        throw error;
    }
}

// 일정 수정
async function updateSchedule(schedule) {
    try {
        const response = await axios.put(`/schedules/${schedule.id}`, schedule);
        return response.data; // 수정된 일정 데이터 반환
    } catch (error) {
        console.error('Failed to update schedule:', error);
        throw error;
    }
}

// 일정 삭제
async function deleteSchedule(scheduleId) {
    try {
        const response = await axios.delete(`/schedules/${scheduleId}`);
        return response.data; // 삭제 성공 여부 반환
    } catch (error) {
        console.error('Failed to delete schedule:', error);
        throw error;
    }
}
