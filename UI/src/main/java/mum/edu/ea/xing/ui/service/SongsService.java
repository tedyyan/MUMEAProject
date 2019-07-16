package mum.edu.ea.xing.ui.service;

import mum.edu.ea.xing.ui.model.Song;

public interface SongsService {
    Song[] getTracks();
    Song getCurrentlyPlayingTrack();
}
