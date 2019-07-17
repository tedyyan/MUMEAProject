package mum.edu.ea.xing.ui.domains;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Authority {
    private Long id;

    private AuthorityType AuthName;

}
