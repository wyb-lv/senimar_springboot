package com.wyb.web.senimar_demo.repository;

import com.wyb.web.senimar_demo.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    boolean existsByUsername(String admin);

    List<Account> searchAccountByUsernameContainingIgnoreCase(String username);
}
