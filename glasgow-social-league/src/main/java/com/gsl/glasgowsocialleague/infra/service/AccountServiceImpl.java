package com.gsl.glasgowsocialleague.infra.service;

import com.gsl.glasgowsocialleague.core.model.account.Account;
import com.gsl.glasgowsocialleague.core.service.AccountService;
import com.gsl.glasgowsocialleague.infra.gateway.AccountGateway;
import com.gsl.glasgowsocialleague.web.security.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final AccountGateway accountGateway;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AccountServiceImpl(AccountGateway accountGateway, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.accountGateway = accountGateway;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public String authenticate(String email, String rawPassword) {
        log.info("Authenticating user with email: {}", email);
        Optional<Account> accountOpt = accountGateway.findByEmail(email);

        if (accountOpt.isPresent()) {
            Account account = accountOpt.get();
            log.info("Account found for email: {}", email);

            // Verify the password by comparing the raw password to the hashed password
            if (passwordEncoder.matches(rawPassword, account.getPassword())) {
                log.info("Password match for email: {}. Generating JWT token.", email);
                // Generate a JWT token
                return jwtTokenProvider.generateToken(account);
            } else {
                log.warn("Password mismatch for email: {}", email);
            }
        } else {
            log.warn("No account found for email: {}", email);
        }

        // Authentication failed
        return null;
    }


    @Override
    public List<Account> getAllAccounts() {
        log.info("Fetching all accounts");
        return accountGateway.findAll();
    }

    @Override
    public Optional<Account> getAccountById(UUID id) {
        log.info("Fetching account with ID: {}", id);
        return accountGateway.findById(id);
    }

    @Override
    public Account createAccount(Account account) {
        log.info("Creating new account with email: {}", account.getEmail());
        account.setCreatedAt(OffsetDateTime.now());
        account.setUpdatedAt(OffsetDateTime.now());

        // Generate a random password
        String rawPassword = generateRandomPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        account.setPassword(encodedPassword);

        Account savedAccount = accountGateway.save(account);
        log.info("Account created successfully with ID: {}", savedAccount.getId());

        // Optionally return the raw password for further handling
        savedAccount.setPassword(rawPassword);
        log.debug("Raw password generated for account with email: {}", account.getEmail());

        return savedAccount;
    }

    @Override
    public Account updateAccount(UUID id, Account accountDetails) {
        log.info("Updating account with ID: {}", id);
        Account account = accountGateway.findById(id)
                .orElseThrow(() -> {
                    log.error("Account not found with ID: {}", id);
                    return new RuntimeException("Account not found with id " + id);
                });

        account.setName(accountDetails.getName());
        account.setEmail(accountDetails.getEmail());
        account.setProfilePicture(accountDetails.getProfilePicture());
        account.setRole(accountDetails.getRole());
        account.setUpdatedAt(OffsetDateTime.now());

        Account updatedAccount = accountGateway.save(account);
        log.info("Account updated successfully with ID: {}", id);
        return updatedAccount;
    }

    @Override
    public void updatePassword(UUID id, String newPassword) {
        log.info("Updating password for account with ID: {}", id);

        // Fetch the account by ID
        Account account = accountGateway.findById(id)
                .orElseThrow(() -> {
                    log.error("Account not found with ID: {}", id);
                    return new RuntimeException("Account not found with id " + id);
                });

        // Encode the new password
        String encodedNewPassword = passwordEncoder.encode(newPassword);

        log.info("encoded new password: {}", encodedNewPassword);

        // Update the account's password
        account.setPassword(encodedNewPassword);
        account.setUpdatedAt(OffsetDateTime.now());

        // Save the updated account
        accountGateway.save(account);
        log.info("Password updated successfully for account with ID: {}", id);
    }

    @Override
    public void deleteAccount(UUID id) {
        log.info("Deleting account with ID: {}", id);
        Account account = accountGateway.findById(id)
                .orElseThrow(() -> {
                    log.error("Account not found with ID: {}", id);
                    return new RuntimeException("Account not found with id " + id);
                });
        accountGateway.delete(account);
        log.info("Account deleted successfully with ID: {}", id);
    }

    private String generateRandomPassword() {
        String randomPassword = UUID.randomUUID().toString().replace("-", "").substring(0, 12);
        log.debug("Generated random password: {}", randomPassword);
        return randomPassword;
    }
}
