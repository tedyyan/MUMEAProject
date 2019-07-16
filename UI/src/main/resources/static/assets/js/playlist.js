function addNewPlaylist(name) {
    fetch('http://localhost:7070/playlist/',{
        method: 'post',
        body: JSON.stringify({'name':name})
    }).then(response=>response.json()).then()
}