package mum.edu.ea.xing.playlistmicroservice.repository;

import mum.edu.ea.xing.playlistmicroservice.domain.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
}
