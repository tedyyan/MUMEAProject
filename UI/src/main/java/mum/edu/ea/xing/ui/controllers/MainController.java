package mum.edu.ea.xing.ui.controllers;

import mum.edu.ea.xing.ui.client.SongClient;
import mum.edu.ea.xing.ui.domains.Playlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private SongClient songClient;

    @GetMapping("/")
    public String indexPage(Model model) {
        model.addAttribute("playlist", new Playlist());
        model.addAttribute("accessToken",songClient.refreshToken());
        model.addAttribute("currentSong",songClient.getCurrentlyPlayingTrack());
        model.addAttribute("songs",songClient.getTracks());
        return "home";
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        return "login";
    }

    @GetMapping("/songs")
    public String songsPage(Model model){
        model.addAttribute("songs",songClient.getTracks());
        return "songs";
    }

    

    @GetMapping("/artists")
    public String artistsPage(Model model) {
        return "parts/library/artists";
    }
}
