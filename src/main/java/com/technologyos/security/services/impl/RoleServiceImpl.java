package com.technologyos.security.services.impl;

import com.technologyos.security.entities.Role;
import com.technologyos.security.repositories.RoleRepository;
import com.technologyos.security.services.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {
   private final RoleRepository roleRepository;

   @Value("${security.default.role}")
   private String defaultRole;

   @Override
   public Optional<Role> findDefaultRole() {
      return roleRepository.findByName(defaultRole);
   }
}
