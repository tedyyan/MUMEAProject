package mum.edu.ea.xing.ui.controllers;

import mum.edu.ea.xing.ui.client.PlaylistClient;
import mum.edu.ea.xing.ui.client.SongClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import mum.edu.ea.xing.ui.domains.Playlist;

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
        return "playlist";
    }
	
	@GetMapping("/detail/{id}")
    public String playlistPage(@PathVariable Long id, Model model) {
		model.addAttribute("playlist", playlistClient.get(id));
		model.addAttribute("currentSong",songClient.getCurrentlyPlayingTrack());
        return "parts/library/playlist/playlist-details";
    }
	
	@PostMapping(value = "/savePlaylist")
	@ResponseBody
	public Playlist savePlaylist(@ModelAttribute String name, Model model) {
		System.out.println(name);
		Playlist playlist = new Playlist();
		playlist.setName(name);
		playlistClient.add(playlist);
		return playlist;
	}
}