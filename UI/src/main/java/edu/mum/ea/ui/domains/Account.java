package edu.mum.ea.ui.domains;

import lombok.Data;

@Data
public class Account {
    private Long id;

    private String userName;
    private String password;
    private String email;
    private int age;
    private String favor;
    Authority authority;

}
