package com.gsl.glasgowsocialleague.infra.service;

import com.gsl.glasgowsocialleague.core.model.auth.Role;
import com.gsl.glasgowsocialleague.core.service.RoleService;
import com.gsl.glasgowsocialleague.infra.gateway.RoleGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleGateway roleGateway;

    @Autowired
    public RoleServiceImpl(RoleGateway roleGateway) {
        this.roleGateway = roleGateway;
    }

    public Role createRole(Role role) {
        return roleGateway.save(role);
    }

    public Optional<Role> getRoleById(Integer id) {
        return roleGateway.findById(id);
    }

    public Optional<Role> getRoleByName(String name) {
        return roleGateway.findByName(name);
    }

    public void deleteRole(Integer id) {
        roleGateway.deleteById(id);
    }

    public boolean hasPermission(Role role, String permission) {
        Map<String, Object> permissions = role.getPermissions();
        return permissions != null && permissions.containsKey(permission);
    }

    public boolean checkAccess(String roleName, String permission) {
        Optional<Role> role = getRoleByName(roleName);
        return role.isPresent() && hasPermission(role.get(), permission);
    }
}
