package com.technologyos.security.services;

import com.technologyos.security.entities.Role;

import java.util.Optional;

public interface RoleService {
   Optional<Role> findDefaultRole();
}
