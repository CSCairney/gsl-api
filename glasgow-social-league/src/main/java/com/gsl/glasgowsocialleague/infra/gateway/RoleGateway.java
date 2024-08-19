package com.gsl.glasgowsocialleague.infra.gateway;

import com.gsl.glasgowsocialleague.core.model.auth.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleGateway extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);
}
