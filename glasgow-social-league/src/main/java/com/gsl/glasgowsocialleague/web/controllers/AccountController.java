package com.gsl.glasgowsocialleague.web.controllers;

import com.gsl.glasgowsocialleague.core.model.account.Account;
import com.gsl.glasgowsocialleague.core.model.auth.LoginRequest;
import com.gsl.glasgowsocialleague.core.service.AccountService;
import com.gsl.glasgowsocialleague.web.dto.LoginResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/accounts")
@Slf4j
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public List<Account> getAllAccounts() {
        log.info("Fetching all accounts");
        return accountService.getAllAccounts();
    }

    @GetMapping("/{id}")
    public Optional<Account> getAccountById(@PathVariable UUID id) {
        log.info("Fetching account with ID: {}", id);
        return accountService.getAccountById(id);
    }

    @PostMapping
    public Account createAccount(@RequestBody Account account) {
        log.info("Creating a new account: {}", account);
        return accountService.createAccount(account);
    }

    @PutMapping("/{id}")
    public Account updateAccount(@PathVariable UUID id, @RequestBody Account account) {
        log.info("Updating account with ID: {} with data: {}", id, account);
        return accountService.updateAccount(id, account);
    }

    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable UUID id) {
        log.info("Deleting account with ID: {}", id);
        accountService.deleteAccount(id);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        log.info("Attempting to log in user with email: {}", loginRequest.getEmail());

        // Authenticate the user using the AccountService
        String token = accountService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());

        if (token == null) {
            // Return 401 Unauthorized if authentication fails
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // Return the JWT token if authentication is successful
        return ResponseEntity.ok(new LoginResponse(token));
    }

}
