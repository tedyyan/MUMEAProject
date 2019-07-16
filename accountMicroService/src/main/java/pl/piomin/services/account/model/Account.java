package pl.piomin.services.account.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Account {
    @Id
    @GeneratedValue
    private Long id;

    private String userName;
    private String password;
    private String email;
    private int age;
    private String favor;
    @OneToMany
    @JoinColumn(name="account_id")
    @Column(nullable = false)
    List<Authority> authorityList;

}
