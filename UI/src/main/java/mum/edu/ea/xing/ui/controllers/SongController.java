package mum.edu.ea.xing.ui.controllers;

import mum.edu.ea.xing.ui.client.SongClient;
import mum.edu.ea.xing.ui.domains.SpotifyToken;
import mum.edu.ea.xing.ui.model.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class SongController {

    @Autowired
    private SongClient songClient;

    @GetMapping("/refreshToken")
    public SpotifyToken refreshToken(){
        return songClient.refreshToken();
    }

    @PostMapping(value = "/setDeviceId/{deviceId}",consumes = {MediaType.APPLICATION_JSON_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE})
    public String setDeviceId(@PathVariable String deviceId, HttpSession session){
        session.setAttribute("deviceId",deviceId);
        return deviceId;
    }

    @GetMapping("/getDeviceId")
    public String getDeviceId(HttpSession session){
        return (String) session.getAttribute("deviceId");
    }

    @GetMapping(value = "/getCurrentlyPlayingTrack")
    public Song getCurrentlyPlayingTrack(){
        return songClient.getCurrentlyPlayingTrack();
    }

    @PutMapping("/pauseTrack/{deviceId}")
    public String pauseTrack(@PathVariable String deviceId){
        return songClient.previousTrack(deviceId);
    }

    @PutMapping(value = "/playTrack/{deviceId}/{songIndex}",consumes = {MediaType.APPLICATION_JSON_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE})
    public String playTrack(@PathVariable String deviceId,@PathVariable String songIndex,@RequestBody Song song){
        return songClient.playTrack(deviceId,song,songIndex);
    }

    @PostMapping("/nextTrack/{deviceId}")
    public String nextTrack(@PathVariable String deviceId){
        return songClient.nextTrack(deviceId);
    }

    @PostMapping("/previousTrack/{deviceId}")
    public String previousTrack(@PathVariable String deviceId){
        return songClient.previousTrack(deviceId);
    }

    @PostMapping("/shuffleTrack/{deviceId}/{state}")
    public String shuffleTrack(@PathVariable String deviceId,@PathVariable boolean state){
        return songClient.shuffleTrack(deviceId,state);
    }

    @PutMapping("/repeatTrack/{deviceId}/{state}")
    public String repeatTrack(@PathVariable String deviceId,@PathVariable String state){
        return songClient.repeatTrack(deviceId,state);
    }
}
