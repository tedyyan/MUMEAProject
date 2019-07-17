package mum.edu.ea.xing.ui.domains;

import lombok.Data;

import java.util.List;

@Data
public class Account {
    private Long id;

    private String userName;
    private String password;
    private String email;
    List<Authority> authorityList;

}
