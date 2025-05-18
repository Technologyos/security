package com.technologyos.security.repositories;

import com.technologyos.security.entities.GrantedPermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<GrantedPermission, Long> {}
