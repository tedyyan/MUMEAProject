package mum.edu.ea.xing.playlistmicroservice.service;

import mum.edu.ea.xing.playlistmicroservice.domain.Playlist;
import mum.edu.ea.xing.playlistmicroservice.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PlaylistService {
    @Autowired
    private PlaylistRepository playlistRepository;

    public List<Playlist> getAll() {
        return  playlistRepository.findAll();
    }

    public void save(Playlist p) {
        playlistRepository.save(p);
    }

    public Playlist get(Long id) {
        return playlistRepository.getOne(id);
    }

    public void update(Playlist p) {
        playlistRepository.save(p);
    }

}
