package pl.piomin.services.account.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Authority {
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private AuthorityType AuthName;
}
