package edu.mum.ea.songs.controller;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.SavedTrack;
import com.wrapper.spotify.requests.data.library.GetUsersSavedTracksRequest;
import edu.mum.ea.songs.util.LoggingRequestInterceptor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@RestController
public class SpotifyRestController {


    @PostMapping("/login")
    public AuthResponse login(Model model) {
        RestTemplate restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new LoggingRequestInterceptor());
        restTemplate.setInterceptors(interceptors);

        String clientId = "9bb4d61b35974a86a44f82ece4a09086";
        String clientSecret = "469e4131940745a69f828df081275ec4";

        StringBuilder builder = new StringBuilder();
        builder.append(clientId);
        builder.append(":");
        builder.append(clientSecret);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Authorization", "Basic " + Base64.getEncoder().encodeToString(builder.toString().getBytes()));

        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("grant_type", "client_credentials");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        AuthResponse authResponse = restTemplate.postForObject("https://accounts.spotify.com/api/token",
                request,AuthResponse.class);
        return authResponse;
    }

    @GetMapping("getSongsTracks/{accessToken}")
    public Paging<SavedTrack> getSongsTracks(@PathVariable String accessToken){

        SpotifyApi spotifyApi = new SpotifyApi.Builder()
                .setAccessToken(accessToken)
                .build();
        GetUsersSavedTracksRequest getUsersSavedTracksRequest = spotifyApi.getUsersSavedTracks()
//          .limit(10)
//          .offset(0)
          .market(CountryCode.US)
                .build();
        try {
            final Paging<SavedTrack> savedTrackPaging = getUsersSavedTracksRequest.execute();

            System.out.println("Total: " + savedTrackPaging.getTotal());
            return savedTrackPaging;
        } catch (IOException | SpotifyWebApiException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
}

@Getter
@Setter
class AuthResponse {
    private String access_token;
    private String token_type;
    private String expires_in;
}