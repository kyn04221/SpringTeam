

async function addRoom(roomRegisterObj) {
    const response = await axios.post(`/matchingRoom/roomRegister`, roomRegisterObj);
    return response.data;
}





