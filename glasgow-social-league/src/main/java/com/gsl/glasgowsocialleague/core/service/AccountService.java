package com.gsl.glasgowsocialleague.core.service;

import com.gsl.glasgowsocialleague.core.model.account.Account;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountService {
    List<Account> getAllAccounts();

    Account authenticate(String email, String rawPassword);

    Optional<Account> getAccountById(UUID id);

    Account createAccount(Account account);

    Account updateAccount(UUID id, Account accountDetails);

    void updatePassword(UUID id, String newPassword) throws NoSuchAlgorithmException;

    void deleteAccount(UUID id);
}
