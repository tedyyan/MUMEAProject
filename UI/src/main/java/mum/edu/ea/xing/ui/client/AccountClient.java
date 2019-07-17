package mum.edu.ea.xing.ui.client;

import mum.edu.ea.xing.ui.domains.Account;
import mum.edu.ea.xing.ui.domains.Playlist;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "account-service")
public interface AccountClient {
    @GetMapping("/account/{userName}")
    public Account findByUserName(@PathVariable String userName) ;

    @PostMapping("/account/add")
    public void saveAccount(@RequestBody Account account) ;
}
