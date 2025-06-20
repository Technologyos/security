package com.technologyos.auth.repositories;

import com.technologyos.auth.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
   Optional<Role> findByName(String defaultRole);

   List<Role> findByStatusId(Long statusId);
}
