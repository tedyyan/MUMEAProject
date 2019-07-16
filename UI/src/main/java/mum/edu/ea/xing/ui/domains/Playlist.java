package mum.edu.ea.xing.ui.domains;

import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Playlist {
	private Long id;
	private String name;
	private Set<Song> songs;
}
