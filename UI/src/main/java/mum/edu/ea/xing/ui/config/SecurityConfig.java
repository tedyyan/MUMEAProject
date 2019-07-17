package mum.edu.ea.xing.ui.config;

import mum.edu.ea.xing.ui.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AccountService accountService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // auth.inMemoryAuthentication()
        //         .withUser("admin").password("{noop}123").roles("USER","ADMIN").and()
        //         .withUser("user").password("{noop}bla").roles("USER");
        auth.userDetailsService(accountService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().antMatchers("/*").hasAnyRole("ADMIN","USER").and()
                .formLogin().loginPage("/login").permitAll().and()
                .logout();
    }


//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.debug(true);
//    }
}
