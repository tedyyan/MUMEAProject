package mum.edu.ea.xing.songsmicroService.controller;

import com.google.gson.JsonParser;
import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import com.wrapper.spotify.model_objects.miscellaneous.CurrentlyPlaying;
import com.wrapper.spotify.model_objects.miscellaneous.CurrentlyPlayingContext;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.SavedTrack;
import com.wrapper.spotify.requests.data.library.GetUsersSavedTracksRequest;
import mum.edu.ea.xing.songsmicroService.util.LoggingRequestInterceptor;
import com.wrapper.spotify.requests.data.player.GetUsersCurrentlyPlayingTrackRequest;
import com.wrapper.spotify.requests.data.player.PauseUsersPlaybackRequest;
import mum.edu.ea.xing.songsmicroService.model.Image;
import mum.edu.ea.xing.songsmicroService.model.Song;
import mum.edu.ea.xing.songsmicroService.model.SpotifyToken;
import mum.edu.ea.xing.songsmicroService.util.SpotifyUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(value = "http://localhost:3200")
public class SpotifyRestController {

    @GetMapping("/refreshToken")
    public SpotifyToken refreshToken(){
        return new SpotifyToken(SpotifyUtil.spotifyApi().getAccessToken(),SpotifyUtil.spotifyApi().getRefreshToken());
    }

    @GetMapping("/getSongsTracks")
    public List<Song> getSongsTracks(){
        try {
            GetUsersSavedTracksRequest getUsersSavedTracksRequest = SpotifyUtil.spotifyApi().getUsersSavedTracks()
                    .limit(50)
//          .offset(0)
                    .market(CountryCode.US)
                    .build();

            Paging<SavedTrack> savedTrackPaging = getUsersSavedTracksRequest.execute();

            System.out.println("Total: " + savedTrackPaging.getTotal());
            System.out.println("Total items: " + savedTrackPaging.getItems().length);

            List<Song> songList = Arrays.stream(savedTrackPaging.getItems()).map(savedTrack ->
                    new Song(savedTrack.getTrack().getId(),savedTrack.getTrack().getArtists()[0].getName(),
                            savedTrack.getTrack().getName(),
                            new Image(savedTrack.getTrack().getAlbum().getImages()[0].getHeight(),savedTrack.getTrack().getAlbum().getImages()[0].getUrl(),savedTrack.getTrack().getAlbum().getImages()[0].getWidth()),
                            savedTrack.getTrack().getUri(),
                            savedTrack.getTrack().getDurationMs()))
                    .collect(Collectors.toList());

            return songList;
        } catch (IOException | SpotifyWebApiException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    @GetMapping("/getCurrentlyPlayingTrack")
    public Song getCurrentlyPlayingTrack(){
        try {
            CurrentlyPlayingContext playing = SpotifyUtil.spotifyApi()
                    .getInformationAboutUsersCurrentPlayback().market(CountryCode.US).build().execute();
            if(playing!=null){
                System.out.println("Timestamp: " + playing.getTimestamp());

                Song song = new Song(playing.getItem().getId(),
                        playing.getItem().getArtists()[0].getName(),
                        playing.getItem().getName(),
                        new Image(playing.getItem().getAlbum().getImages()[0].getHeight(),playing.getItem().getAlbum().getImages()[0].getUrl(),
                                playing.getItem().getAlbum().getImages()[0].getWidth()),
                        playing.getItem().getUri(),
                        playing.getIs_playing(),
                        playing.getItem().getDurationMs(),
                        playing.getProgress_ms());
                return song;
            }
            return new Song();
        } catch (IOException | SpotifyWebApiException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    @PutMapping("/pauseTrack/{deviceId}")
    public String pauseTrack(@PathVariable String deviceId){
        try {
            return SpotifyUtil.spotifyApi().pauseUsersPlayback()
                    .device_id(deviceId).build().execute();
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @PutMapping("/playTrack/{deviceId}")
    public String playTrack(@PathVariable String deviceId,@RequestBody Song song){
        try {
            return SpotifyUtil.spotifyApi().startResumeUsersPlayback()
                    .device_id(deviceId)
                    .uris(new JsonParser().parse("[\""+song.getUri()+"\"]").getAsJsonArray()).build().execute();
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @PostMapping("/nextTrack/{deviceId}")
    public String nextTrack(@PathVariable String deviceId){
        try {
            return SpotifyUtil.spotifyApi().skipUsersPlaybackToNextTrack()
                    .device_id(deviceId).build().execute();
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @PostMapping("/previousTrack/{deviceId}")
    public String previousTrack(@PathVariable String deviceId){
        try {
            return SpotifyUtil.spotifyApi().skipUsersPlaybackToPreviousTrack()
                    .device_id(deviceId).build().execute();
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @PostMapping("/shuffleTrack/{deviceId}/{state}")
    public String shuffleTrack(@PathVariable String deviceId,@PathVariable boolean state){
        try {
            return SpotifyUtil.spotifyApi().toggleShuffleForUsersPlayback(state)
                    .device_id(deviceId).build().execute();
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @PutMapping("/repeatTrack/{deviceId}/{state}")
    public String repeatTrack(@PathVariable String deviceId,@PathVariable String state){
        try {
            return SpotifyUtil.spotifyApi().setRepeatModeOnUsersPlayback(state)
                    .device_id(deviceId).build().execute();
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
}