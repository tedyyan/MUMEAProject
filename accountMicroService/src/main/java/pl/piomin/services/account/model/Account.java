package pl.piomin.services.account.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Account {
    @Id
    @GeneratedValue
    private Long id;

    private String userName;
    private String password;
    private String email;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="account_id")
    @Column(nullable = false)
    List<Authority> authorityList;

}
