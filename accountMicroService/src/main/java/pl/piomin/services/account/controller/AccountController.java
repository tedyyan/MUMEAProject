package pl.piomin.services.account.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.piomin.services.account.model.Account;
import pl.piomin.services.account.repository.AccountRepository;


@RestController
@RequestMapping("/account")
public class AccountController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    AccountRepository accountRepository;

    @PostMapping("/add")
    public Account add(@RequestBody Account account) {
        LOGGER.info("Account add: {}", account);
        return accountRepository.save(account);
    }

    @GetMapping("/{userName}")
    public Account findByUserName(@PathVariable("userName") String userName) {
        LOGGER.info("Account find: id={}", userName);
        Account a = accountRepository.findByUserName(userName);
        LOGGER.info("Account find: id={}", a);
        return a;
    }

    @PostMapping("/update")
    public Account update(Account account) {
        LOGGER.info("Account update");
        return accountRepository.save(account);
    }

    @PostMapping("/delete")
    public void delete(Account account) {
        LOGGER.info("Account update");
        accountRepository.delete(account);
    }
}
