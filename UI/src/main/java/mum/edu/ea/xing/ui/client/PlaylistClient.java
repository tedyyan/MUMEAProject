package mum.edu.ea.xing.ui.client;

import mum.edu.ea.xing.ui.domains.Playlist;
import mum.edu.ea.xing.ui.domains.SpotifyToken;
import mum.edu.ea.xing.ui.model.Song;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "playlist-service")
public interface PlaylistClient {

    @GetMapping("/serverPlaylist/")
    public List<Playlist> getAll() ;

    @GetMapping("/serverPlaylist/{id}")
    public Playlist get(@PathVariable Long id) ;

    @PostMapping("/serverPlaylist/")
    public Playlist add(@RequestBody Playlist playlist);
}
