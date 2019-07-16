package mum.edu.ea.xing.ui.controllers;

import mum.edu.ea.xing.ui.service.SongsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private SongsService songsService;

    @GetMapping("/")
    public String indexPage(Model model) {
        model.addAttribute("currentSong",songsService.getCurrentlyPlayingTrack());
        return "home";
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        return "login";
    }

    @GetMapping("/songs")
    public String songsPage(Model model){
        model.addAttribute("songs",songsService.getTracks());
        return "parts/library/songs";
    }

    

    @GetMapping("/artists")
    public String artistsPage(Model model) {
        return "parts/library/artists";
    }
}
