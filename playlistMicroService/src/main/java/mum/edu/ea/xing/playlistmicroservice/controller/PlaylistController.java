package mum.edu.ea.xing.playlistmicroservice.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonView;
import mum.edu.ea.xing.playlistmicroservice.domain.Playlist;
import mum.edu.ea.xing.playlistmicroservice.domain.Song;
import mum.edu.ea.xing.playlistmicroservice.service.PlaylistService;
import mum.edu.ea.xing.playlistmicroservice.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequestMapping("/playlist")
public class PlaylistController {
    @Autowired
    private PlaylistService playlistService;
    @Autowired
    private SongService songService;

    @GetMapping("/")
    @JsonView(Playlist.PlaylistSimpleView.class)
    public List<Playlist> getAllList() {
        return playlistService.getAll();
    }

    @GetMapping("/{id}")
    public Playlist get(@PathVariable Long id) {
        Playlist p =  playlistService.get(id);
        return p;
    }

    @PostMapping("/")
    public RedirectView savePlaylist(Playlist playlist) {
        playlistService.save(playlist);
        return  new RedirectView("/playlist/" + playlist.getId());
    }



    @PostMapping("/{p_id}")
    public RedirectView sageSong(@PathVariable Long p_id, Song song) {
        Song s = songService.get(song.getId());
        Playlist p =  playlistService.get(p_id);
        p.getSongs().add(s);
        playlistService.update(p);
        return  new RedirectView("/playlist/" + p_id);
    }
}
