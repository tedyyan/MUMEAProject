package mum.edu.ea.xing.ui.controllers;

import mum.edu.ea.xing.ui.client.SongClient;
import mum.edu.ea.xing.ui.domains.Playlist;
import mum.edu.ea.xing.ui.domains.Account;
import mum.edu.ea.xing.ui.domains.Playlist;
import mum.edu.ea.xing.ui.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class MainController {

    @Autowired
    private SongClient songClient;
    @Autowired
    private AccountService accountService;

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

    @GetMapping("/register")
    public String register(Model model) {
        return "register";
    }

    @PostMapping("/register")
    public String registerPost(@Valid @ModelAttribute Account account, Model model) {
        Account checkAccount = accountService.findAccount(account.getUserName());
        if (checkAccount != null) {
            model.addAttribute("error","error");
            return "register";
        } else {
            accountService.saveAccount(account);
        }
        return "redirect:login";
    }

    @GetMapping("/logout")
    public String logout(Model model, HttpSession httpSession) {
        SecurityContextHolder.clearContext();
        httpSession.invalidate();
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
