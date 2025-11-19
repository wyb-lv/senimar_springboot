package com.wyb.web.senimar_demo.services;

import com.wyb.web.senimar_demo.entity.Account;
import com.wyb.web.senimar_demo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    /**
     * Get all accounts
     */
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    /**
     * Save or update account
     */
    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

    /**
     * Delete account by ID
     */
    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }


    /**
     * Get all customers (all accounts)
     */
    public List<Account> getAllCustomers() {
        return accountRepository.findAll();
    }

    public boolean usernameExists(String admin) {
        return accountRepository.existsByUsername(admin);
    }

    public List<Account> searchCustomersbyUsername(String username) {
        return accountRepository.searchAccountByUsernameContainingIgnoreCase(username);
    }



}