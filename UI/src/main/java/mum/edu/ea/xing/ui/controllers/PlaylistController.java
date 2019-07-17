package mum.edu.ea.xing.ui.controllers;

import mum.edu.ea.xing.ui.client.PlaylistClient;
import mum.edu.ea.xing.ui.client.SongClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import mum.edu.ea.xing.ui.domains.Playlist;

@Controller
@RequestMapping("/playlist")
public class PlaylistController {
	@Autowired
	private PlaylistClient playlistClient;

    @Autowired
    private SongClient songClient;
	
	@GetMapping(value = {"","/"})
    public String playlistPage(Model model) {
		model.addAttribute("playlist", new Playlist());
		model.addAttribute("list", playlistClient.getAll());
        return "playlist";
    }
	
	@GetMapping("/detail/{id}")
    public String playlistPage(@PathVariable Long id, Model model) {
		model.addAttribute("playlist", playlistClient.get(id));
		model.addAttribute("currentSong",songClient.getCurrentlyPlayingTrack());
        return "parts/library/playlist/playlist-details";
    }
	
	@PostMapping(value = "/savePlaylist")
	public String savePlaylist(Playlist playlist, Model model) {
		System.out.println("2222222222");
		System.out.println(playlist.getName());
		playlistClient.add(playlist);
		return "redirect:/playlist";
	}
}