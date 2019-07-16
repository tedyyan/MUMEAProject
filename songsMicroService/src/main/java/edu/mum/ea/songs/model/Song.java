package edu.mum.ea.songs.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Song {

    private String songId;
    private String artist;
    private String name;
    private Image image;
    private String uri;
    private String[] uris;
    private boolean isPlaying;
    private Integer duration;
    private Integer progress;

    public Song(String songId, String artist, String name, Image image, String uri, boolean isPlaying, Integer duration, Integer progress) {
        this.songId = songId;
        this.artist = artist;
        this.name = name;
        this.image = image;
        this.uri = uri;
        this.isPlaying = isPlaying;
        this.duration = duration;
        this.progress = progress;
    }

    public Song(String songId, String artist, String name, Image image, String uri, Integer duration) {
        this.songId = songId;
        this.artist = artist;
        this.name = name;
        this.image = image;
        this.uri = uri;
        this.duration = duration;
    }
}