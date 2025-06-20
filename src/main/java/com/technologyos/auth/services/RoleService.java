package com.technologyos.auth.services;

import com.technologyos.auth.dtos.role.RoleRequest;
import com.technologyos.auth.entities.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RoleService {
   Role findDefaultRole();

   Page<Role> findAll(Pageable pageable);

   Role findRoleById(Long roleId);

   Role createRole(RoleRequest roleRequest);

   Role updateRole(RoleRequest roleRequest, Long roleId);

   Role disableById(Long roleId);

   Role enableById(Long roleId);

   List<Role> findByStatusId(Long statusId);
}
