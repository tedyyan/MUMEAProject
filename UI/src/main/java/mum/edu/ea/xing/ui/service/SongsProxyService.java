package mum.edu.ea.xing.ui.service;

import mum.edu.ea.xing.ui.model.Song;
import mum.edu.ea.xing.ui.util.LoggingRequestInterceptor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class SongsProxyService implements SongsService {

    private static final String BASE_URL = "http://localhost:9090/";

    @Override
    public Song[] getTracks(){
        RestTemplate restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new LoggingRequestInterceptor());
        restTemplate.setInterceptors(interceptors);
        ResponseEntity<Song[]> songsList = restTemplate.getForEntity(BASE_URL+"getSongsTracks", Song[].class);
        return songsList.getBody();
    }

    @Override
    public Song getCurrentlyPlayingTrack() {
        RestTemplate restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new LoggingRequestInterceptor());
        restTemplate.setInterceptors(interceptors);

        return restTemplate.getForObject(BASE_URL+"getCurrentlyPlayingTrack",Song.class);
    }
}
