package edu.mum.ea.ui.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import edu.mum.ea.ui.domains.Playlist;

@Service
public class PlaylistServiceProxy {
	@Autowired
	private RestTemplate restTemplate;
	
	private final String playlistsUrl = "http://localhost:9090/playlist/";
	private final String playlistUrl = "http://localhost:9090/playlist/{id}";
	
	public List<Playlist> getAll() {
		ResponseEntity<List<Playlist>> response =
                restTemplate.exchange(playlistsUrl, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<Playlist>>() {
                        });
        return response.getBody();
	}
	
	public Playlist get(Long id) {
        return restTemplate.getForObject(playlistUrl, Playlist.class, id);
    }

}
