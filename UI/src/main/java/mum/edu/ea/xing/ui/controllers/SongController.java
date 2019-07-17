package mum.edu.ea.xing.ui.controllers;

import mum.edu.ea.xing.ui.client.SongClient;
import mum.edu.ea.xing.ui.domains.SpotifyToken;
import mum.edu.ea.xing.ui.model.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class SongController {

    @Autowired
    private SongClient songClient;

    @GetMapping("/refreshToken")
    public SpotifyToken refreshToken(){
        return songClient.refreshToken();
    }

    @GetMapping("/getCurrentlyPlayingTrack")
    public Song getCurrentlyPlayingTrack(){
        return songClient.getCurrentlyPlayingTrack();
    }

    @PutMapping("/pauseTrack/{deviceId}")
    public String pauseTrack(@PathVariable String deviceId){
        return songClient.previousTrack(deviceId);
    }

    @PutMapping("/playTrack/{deviceId}")
    public String playTrack(@PathVariable String deviceId,@RequestBody Song song){
        return songClient.playTrack(deviceId,song);
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
