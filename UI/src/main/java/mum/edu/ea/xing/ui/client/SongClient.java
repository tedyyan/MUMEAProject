package mum.edu.ea.xing.ui.client;

import mum.edu.ea.xing.ui.domains.SpotifyToken;
import mum.edu.ea.xing.ui.model.Song;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "songs-service")
public interface SongClient {

    @GetMapping("/getSongsTracks")
    List<Song> getTracks();

    @GetMapping("/getCurrentlyPlayingTrack")
    Song getCurrentlyPlayingTrack();

    @GetMapping("/refreshToken")
    SpotifyToken refreshToken();

    @PutMapping("/playTrack/{deviceId}/{index}")
    String playTrack(@PathVariable String deviceId, @RequestBody Song song,@PathVariable String index);

    @PostMapping("/nextTrack/{deviceId}")
    public String nextTrack(@PathVariable String deviceId);

    @PostMapping("/previousTrack/{deviceId}")
    public String previousTrack(@PathVariable String deviceId);

    @PostMapping("/shuffleTrack/{deviceId}/{state}")
    public String shuffleTrack(@PathVariable String deviceId,@PathVariable boolean state);

    @PutMapping("/repeatTrack/{deviceId}/{state}")
    public String repeatTrack(@PathVariable String deviceId,@PathVariable String state);
}
