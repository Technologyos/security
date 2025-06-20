package com.technologyos.auth.services.impl;

import com.technologyos.auth.dtos.role.RoleRequest;
import com.technologyos.auth.dtos.status.StatusEnum;
import com.technologyos.auth.entities.Role;
import com.technologyos.auth.exceptions.ObjectNotFoundException;
import com.technologyos.auth.repositories.RoleRepository;
import com.technologyos.auth.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
   @Autowired
   private RoleRepository roleRepository;

   @Value("${security.default.role}")
   private String defaultRole;

   @Override
   public Role findDefaultRole() {
      return roleRepository.findByName(defaultRole)
         .orElseThrow(() -> new ObjectNotFoundException(HttpStatus.NOT_FOUND.value(),
            "role not found by defaultRole " + defaultRole, HttpStatus.NOT_FOUND));
   }

   @Override
   public Page<Role> findAll(Pageable pageable) {
      return roleRepository.findAll(pageable);
   }

   @Override
   public Role findRoleById(Long roleId) {
      return roleRepository.findById(roleId)
         .orElseThrow(() -> new ObjectNotFoundException(HttpStatus.NOT_FOUND.value(),
            "role not found by id " + roleId, HttpStatus.NOT_FOUND));
   }

   @Override
   public Role createRole(RoleRequest roleRequest) {
      Role role = new Role();
      role.setName(roleRequest.getName());
      role.setStatusId(StatusEnum.ENABLED.getCode());
      return roleRepository.save(role);
   }

   @Override
   public Role updateRole(RoleRequest roleRequest, Long roleId) {
      Role currentRole = this.findRoleById(roleId);
      currentRole.setName(roleRequest.getName());
      currentRole.setStatusId(roleRequest.getStatus().getCode());
      return roleRepository.save(currentRole);
   }

   @Override
   public Role disableById(Long roleId) {
      Role currentRole = this.findRoleById(roleId);
      currentRole.setStatusId(StatusEnum.DISABLED.getCode());
      return roleRepository.save(currentRole);
   }

   @Override
   public Role enableById(Long roleId) {
      Role currentRole = this.findRoleById(roleId);
      currentRole.setStatusId(StatusEnum.ENABLED.getCode());
      return roleRepository.save(currentRole);
   }

   @Override
   public List<Role> findByStatusId(Long statusId) {
      return roleRepository.findByStatusId(statusId);
   }
}
