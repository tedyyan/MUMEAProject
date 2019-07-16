package edu.mum.ea.ui.model;

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
    private boolean isPlaying;
    private Integer duration;
    private Integer progress;
}