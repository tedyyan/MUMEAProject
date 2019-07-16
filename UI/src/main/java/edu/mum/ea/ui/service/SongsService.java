package edu.mum.ea.ui.service;

import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.SavedTrack;
import edu.mum.ea.ui.model.Song;

public interface SongsService {
    Song[] getTracks();
    Song getCurrentlyPlayingTrack();
}
