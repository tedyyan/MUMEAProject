package mum.edu.ea.xing.ui.controllers;

import mum.edu.ea.xing.ui.client.PlaylistClient;
import mum.edu.ea.xing.ui.client.SongClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import mum.edu.ea.xing.ui.domains.Playlist;

import javax.validation.Valid;

@Controller
@RequestMapping("/playlist")
public class PlaylistController {
	@Autowired
	private PlaylistClient playlistClient;

    @Autowired
    private SongClient songClient;
	
	@GetMapping
    public String playlistPage(Model model) {
		model.addAttribute("playlist", new Playlist());
		model.addAttribute("list", playlistClient.getAll());
        return "parts/library/playlist";
    }
	
	@GetMapping("/detail/{id}")
    public String playlistPage(@PathVariable Long id, Model model) {
		model.addAttribute("playlist", playlistClient.get(id));
		model.addAttribute("currentSong",songClient.getCurrentlyPlayingTrack());
        return "parts/library/playlist/playlist-details";
    }
	
	@PostMapping("/")
	public String savePlaylist(@Valid @ModelAttribute("playlist") Playlist playlist, Model model) {
		playlistClient.add(playlist);
		return "redirect:/playlist";
	}
}