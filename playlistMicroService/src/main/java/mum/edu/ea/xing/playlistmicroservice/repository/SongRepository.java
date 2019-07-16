package mum.edu.ea.xing.playlistmicroservice.repository;

import mum.edu.ea.xing.playlistmicroservice.domain.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
}
