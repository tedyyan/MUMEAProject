package mum.edu.ea.xing.ui.client;

import mum.edu.ea.xing.ui.model.Song;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "songs")
public interface SongClient {

    @GetMapping("/getSongsTracks")
    List<Song> getTracks();
}
