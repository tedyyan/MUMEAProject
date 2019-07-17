package mum.edu.ea.xing.ui.service;

import mum.edu.ea.xing.ui.client.AccountClient;
import mum.edu.ea.xing.ui.domains.Account;
import mum.edu.ea.xing.ui.domains.Authority;
import mum.edu.ea.xing.ui.domains.AuthorityType;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService implements UserDetailsService {

    @Autowired
    private AccountClient accountClient;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        List<GrantedAuthority> authorities = new ArrayList<>();

        // TODO start just for test
//        if (userName.equals("admin")) {
//            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//            return new User("admin", encoder.encode("123"), authorities);
//        } else if (userName.equals("user")) {
//            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//            return new User("user", encoder.encode("bla"), authorities);
//        }
        // TODO end
        Account account = accountClient.findByUserName(userName);
        for(Authority a : account.getAuthorityList()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + a.getAuthName().getName()));
        }
        return new User(account.getUserName(), encoder.encode(account.getPassword()), authorities);
    }

    public void saveAccount(Account account) {
        account.setAuthorityList(new ArrayList<>());
        account.getAuthorityList().add(new Authority(null, AuthorityType.USER));
        accountClient.saveAccount(account);
    }

    public Account findAccount(String userName) {
        return accountClient.findByUserName(userName);
    }
}
