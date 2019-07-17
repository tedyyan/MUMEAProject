package mum.edu.ea.xing.ui.service;

import java.net.URI;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import mum.edu.ea.xing.ui.domains.Playlist;

@Service
public class PlaylistServiceProxy {
    @Autowired
    private RestTemplate restTemplate;

    private final String playlistsUrl = "http://172.19.144.168:7071/serverPlaylist/";
    private final String playlistUrl = "http://172.19.144.168:7071/serverPlaylist/{id}";

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

    public void add(Playlist p) {
        URI uri = restTemplate.postForLocation(playlistsUrl, p);
        if (uri == null) {
            return;
        }
        Matcher m = Pattern.compile(".*/playlist/(\\d+)").matcher(uri.getPath());
        m.matches();
        m.group(1);
    }

}
