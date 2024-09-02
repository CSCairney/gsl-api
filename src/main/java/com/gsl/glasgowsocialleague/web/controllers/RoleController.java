package com.gsl.glasgowsocialleague.web.controllers;

import com.gsl.glasgowsocialleague.core.model.auth.Role;
import com.gsl.glasgowsocialleague.core.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/role")
@Slf4j
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        log.info("Creating a new role with name: {}", role.getName());
        Role createdRole = roleService.createRole(role);
        log.info("Created role with ID: {}", createdRole.getId());
        return ResponseEntity.ok(createdRole);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable Integer id) {
        log.info("Fetching role with ID: {}", id);
        Optional<Role> role = roleService.getRoleById(id);
        return role.map(r -> {
            log.info("Found role: {}", r.getName());
            return ResponseEntity.ok(r);
        }).orElseGet(() -> {
            log.warn("Role with ID: {} not found", id);
            return ResponseEntity.notFound().build();
        });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Integer id) {
        log.info("Deleting role with ID: {}", id);
        roleService.deleteRole(id);
        log.info("Deleted role with ID: {}", id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/check-access")
    public ResponseEntity<Boolean> checkAccess(@RequestParam String roleName, @RequestParam String permission) {
        log.info("Checking access for role: {} with permission: {}", roleName, permission);
        boolean hasAccess = roleService.checkAccess(roleName, permission);
        log.info("Access check result for role: {} with permission: {} is: {}", roleName, permission, hasAccess);
        return ResponseEntity.ok(hasAccess);
    }
}
