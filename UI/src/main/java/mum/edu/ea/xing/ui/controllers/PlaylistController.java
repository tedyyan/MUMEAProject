package mum.edu.ea.xing.ui.controllers;

import mum.edu.ea.xing.ui.client.PlaylistClient;
import mum.edu.ea.xing.ui.client.SongClient;
import mum.edu.ea.xing.ui.service.PlaylistServiceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import mum.edu.ea.xing.ui.domains.Playlist;

@Controller
public class PlaylistController {
	@Autowired
	private PlaylistClient playlistClient;

    @Autowired
    private SongClient songClient;

    @Autowired
	private PlaylistServiceProxy playlistServiceProxy;
	
	@GetMapping("/playlist")
    public String playlistPage(Model model) {
		model.addAttribute("playlist", new Playlist());
		model.addAttribute("list", playlistClient.getAll());
        return "playlist";
    }

	@GetMapping("/playlist/detail/{id}")
	public String playlistDetailsPage(@PathVariable Long id, Model model) {
//		model.addAttribute("playlist", playlistClient.get(id));
//		model.addAttribute("currentSong",songClient.getCurrentlyPlayingTrack());
		return "playlist-details";
	}
	
	@PostMapping(value = "/playlist/savePlaylist")
	public String savePlaylist(Playlist playlist, Model model) {
		System.out.println(playlist.getName());
		playlistServiceProxy.add(playlist);
		return "redirect:/playlist";
	}
}