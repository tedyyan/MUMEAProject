package mum.edu.ea.xing.ui.service;

import mum.edu.ea.xing.ui.domains.Account;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class AccountService implements UserDetailsService {
    private static final String BASE_URL = "http://localhost:8090/account/{userName}";

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        RestTemplate restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));

        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        List<GrantedAuthority> authorities = new ArrayList<>();

        // TODO start just for test
        if (userName.equals("admin")) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            return new User("admin", encoder.encode("123"), authorities);
        } else if (userName.equals("user")) {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            return new User("user", encoder.encode("bla"), authorities);
        }
        // TODO end
        Account account = restTemplate.getForObject(BASE_URL, Account.class, userName);
        authorities.add(new SimpleGrantedAuthority("ROLE_" + account.getAuthority().getAuthName().getName()));

        return new User(account.getUserName(), encoder.encode(account.getPassword()), authorities);
    }
}
