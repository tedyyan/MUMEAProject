package mum.edu.ea.xing.playlistmicroservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
public class Playlist {
    public interface PlaylistSimpleView{};
    public interface PlaylistDetailView extends PlaylistSimpleView{};

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(PlaylistSimpleView.class)
    private Long id;

    @JsonView(PlaylistSimpleView.class)
    private String name;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonView(PlaylistDetailView.class)
    private Set<Song> songs = new HashSet<Song>();

}
