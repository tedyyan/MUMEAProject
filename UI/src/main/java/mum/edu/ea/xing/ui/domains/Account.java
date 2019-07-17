package mum.edu.ea.xing.ui.domains;

import lombok.Data;

@Data
public class Account {
    private Long id;

    private String userName;
    private String password;
    private String email;
    Authority authority;

}
