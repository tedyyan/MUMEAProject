package com.bolsadeideas.springboot.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String indexPage(Model model) {
        return "home";
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        return "login";
    }

    @GetMapping("/songs")
    public String songsPage(Model model) {
        return "parts/library/songs";
    }

    @GetMapping("/playlist")
    public String playlistPage(Model model) {
        return "parts/library/playlist";
    }

    @GetMapping("/artists")
    public String artistsPage(Model model) {
        return "parts/library/artists";
    }
}
