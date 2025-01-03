async function addRoom(roomRegisterObj) {
    const response = await axios.post(`/matchingRoom/roomRegister`, roomRegisterObj);
    return response.data;
}

async function UpdateRoom(roomInfoObj){
    const response = await axios.put(`/matchingRoom/roomUpdate`,roomInfoObj)
    return response.data
}


// 삭제 요청을 보내는 함수
async function deleteRoom(roomId) {
    const response = await axios.delete(`/matchingRoom/roomDelete/${roomId}`);  // Send roomId in the URL
    return response.data;
}







