var deviceId = null;
window.onSpotifyWebPlaybackSDKReady = async () => {
    const response = await fetch('http://localhost:9090/refreshToken');
    const token = await response.json();
    const accessToken = token.accessToken;

    const player = new Spotify.Player({
        name: 'Mumitify',
        getOAuthToken: cb => { cb(accessToken); }
    });

    player.addListener('ready', ({ device_id }) => {
        deviceId = device_id;
        console.log(deviceId);
        localStorage.setItem('deviceId',device_id);
        fetch(`http://localhost:3200/getCurrentlyPlayingTrack`,
            {
                method: 'POST',
                headers: { 'Content-Type': 'application/json'}
                //body: JSON.stringify({ uris: [trackURI] })
            }
        )
    });

    let connected = await player.connect();
    if (connected) {
        console.log('success');
    }
};
//
// const playTrack = async trackURI => {
//     console.log('playing track', trackURI);
//     fetch(`http://172.19.144.168:9090/playTrack/${deviceId}`,
//         {
//             method: 'PUT',
//             headers: { 'Content-Type': 'application/json'},
//             body: JSON.stringify({ uri: trackURI })
//             //body: JSON.stringify({ uris: [trackURI] })
//         }
//     )
// };
//
// const pauseTrack = async () => {
//     fetch(`http://172.19.144.168:9090/pauseTrack/${deviceId}`,
//         {
//             method: 'PUT'
//         }
//     )
// };

window.onload = function () {
    // var div = document.getElementById("playBtn");
    // div.onclick = function() {
    //     if($('#playBtn .icon-play').is(":visible")){
    //         playTrack('spotify:track:1HVLmKLkPyFbfO4IyLksG7');
    //         $('#playBtn .icon-play').hide();
    //         $('#playBtn .icon-pause').show();
    //     }else{
    //         pauseTrack();
    //         $('#playBtn .icon-pause').hide();
    //         $('#playBtn .icon-play').show();
    //     }
    // };
};