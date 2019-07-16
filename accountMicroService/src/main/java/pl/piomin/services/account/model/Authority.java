package pl.piomin.services.account.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Authority {
    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    private AuthorityType AuthName;
}
