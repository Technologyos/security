package com.technologyos.auth.services;

import com.technologyos.auth.entities.Role;

import java.util.Optional;

public interface RoleService {
   Optional<Role> findDefaultRole();
}
