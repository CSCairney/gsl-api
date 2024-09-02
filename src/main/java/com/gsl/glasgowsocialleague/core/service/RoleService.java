package com.gsl.glasgowsocialleague.core.service;

import com.gsl.glasgowsocialleague.core.model.auth.Role;

import java.util.Optional;

public interface RoleService {

    public Role createRole(Role role);

    public Optional<Role> getRoleById(Integer id);

    public Optional<Role> getRoleByName(String name);

    public void deleteRole(Integer id);

    public boolean hasPermission(Role role, String permission);

    public boolean checkAccess(String roleName, String permission);
}
