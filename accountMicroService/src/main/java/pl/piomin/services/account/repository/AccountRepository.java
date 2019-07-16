package pl.piomin.services.account.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.piomin.services.account.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query("Select a from Account a where a.userName = ?1")
    Account findByUserName(String userName);
}
