package edu.mum.ea.ui.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.mum.ea.ui.service.PlaylistServiceProxy;

@Controller
@RequestMapping("/playlist")
public class PlaylistController {
	@Autowired
	private PlaylistServiceProxy PlaylistService;
	
	@GetMapping(value = {"/", ""})
    public String playlistPage(Model model) {
		model.addAttribute("list", PlaylistService.getAll());
        return "parts/library/playlist";
    }
	
	@GetMapping("/detail/{id}")
    public String playlistPage(@PathVariable Long id, Model model) {
		model.addAttribute("playlist", PlaylistService.get(id));
        return "parts/library/playlist-detail";
    }
	
}