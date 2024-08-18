package com.gsl.glasgowsocialleague.infra.service;

import com.gsl.glasgowsocialleague.core.model.account.Account;
import com.gsl.glasgowsocialleague.core.service.AccountService;
import com.gsl.glasgowsocialleague.infra.gateway.AccountGateway;
import com.gsl.glasgowsocialleague.infra.gateway.RoleGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountGateway accountGateway;
    private final RoleGateway roleGateway;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AccountServiceImpl(AccountGateway accountGateway, RoleGateway roleGateway, PasswordEncoder passwordEncoder) {
        this.accountGateway = accountGateway;
        this.roleGateway = roleGateway;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountGateway.findAll();
    }

    @Override
    public Optional<Account> getAccountById(UUID id) {
        return accountGateway.findById(id);
    }

    @Override
    public Account createAccount(Account account) {
        account.setCreatedAt(OffsetDateTime.now());
        account.setUpdatedAt(OffsetDateTime.now());

        // Generate a random password
        String rawPassword = generateRandomPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        account.setPassword(encodedPassword);

        return accountGateway.save(account);
    }

    @Override
    public Account updateAccount(UUID id, Account accountDetails) {
        Account account = accountGateway.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found with id " + id));

        account.setName(accountDetails.getName());
        account.setEmail(accountDetails.getEmail());
        account.setProfilePicture(accountDetails.getProfilePicture());
        account.setRole(accountDetails.getRole());
        account.setUpdatedAt(OffsetDateTime.now());

        return accountGateway.save(account);
    }

    @Override
    public void deleteAccount(UUID id) {
        Account account = accountGateway.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found with id " + id));
        accountGateway.delete(account);
    }

    private String generateRandomPassword() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 12);
    }
}
