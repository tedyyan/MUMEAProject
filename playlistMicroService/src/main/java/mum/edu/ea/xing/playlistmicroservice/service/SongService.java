package mum.edu.ea.xing.playlistmicroservice.service;

import mum.edu.ea.xing.playlistmicroservice.domain.Song;
import mum.edu.ea.xing.playlistmicroservice.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class SongService {
    @Autowired
    private SongRepository songRepository;

    public Song get(Long id) {
        return songRepository.getOne(id);
    }
}
