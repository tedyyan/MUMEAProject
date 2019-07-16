package mum.edu.ea.xing.ui.controllers;

import mum.edu.ea.xing.ui.service.SongsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import mum.edu.ea.xing.ui.domains.Playlist;
import mum.edu.ea.xing.ui.service.PlaylistServiceProxy;

import javax.validation.Valid;

@Controller
@RequestMapping("/playlist")
public class PlaylistController {
	@Autowired
	private PlaylistServiceProxy playlistService;

	@Autowired
	private SongsService songsService;
	
	@GetMapping
    public String playlistPage(Model model) {
		model.addAttribute("playlist", new Playlist());
		model.addAttribute("list", playlistService.getAll());
        return "parts/library/playlist";
    }
	
	@GetMapping("/detail/{id}")
    public String playlistPage(@PathVariable Long id, Model model) {
		model.addAttribute("playlist", playlistService.get(id));
		model.addAttribute("currentSong",songsService.getCurrentlyPlayingTrack());
        return "parts/library/playlist/playlist-details";
    }
	
	@PostMapping("/")
	public String savePlaylist(@Valid @ModelAttribute("playlist") Playlist playlist, Model model) {
		playlistService.add(playlist);
		return "redirect:/playlist";
	}
}