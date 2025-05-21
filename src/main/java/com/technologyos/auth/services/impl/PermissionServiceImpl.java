package com.technologyos.auth.services.impl;

import com.technologyos.auth.dtos.permissions.PermissionRequest;
import com.technologyos.auth.dtos.permissions.PermissionResponse;
import com.technologyos.auth.entities.GrantedPermission;
import com.technologyos.auth.entities.Operation;
import com.technologyos.auth.entities.Role;
import com.technologyos.auth.exceptions.ObjectNotFoundException;
import com.technologyos.auth.repositories.OperationRepository;
import com.technologyos.auth.repositories.PermissionRepository;
import com.technologyos.auth.repositories.RoleRepository;
import com.technologyos.auth.services.PermissionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class PermissionServiceImpl implements PermissionService {
   private final PermissionRepository permissionRepository;
   private final RoleRepository roleRepository;
   private final OperationRepository operationRepository;

   @Override
   public Page<PermissionResponse> findAll(Pageable pageable) {
      return permissionRepository.findAll(pageable)
         .map(this::entityToPermissionResponse);
   }

   @Override
   public Optional<PermissionResponse> findPermissionById(Long permissionId) {
      return permissionRepository.findById(permissionId)
         .map(this::entityToPermissionResponse);
   }

   @Override
   public PermissionResponse createPermission(PermissionRequest permissionRequest) {
      GrantedPermission newPermission = new GrantedPermission();

      Operation operation = operationRepository.findByName(permissionRequest.getOperation())
         .orElseThrow(() -> new ObjectNotFoundException("Operation not found. Operation: " + permissionRequest.getOperation()));

      newPermission.setOperation(operation);

      Role role = roleRepository.findByName(permissionRequest.getRole()).orElseThrow(
         () -> new ObjectNotFoundException("Role not found. Role: " + permissionRequest.getRole()));
      newPermission.setRole(role);

      permissionRepository.save(newPermission);
      return this.entityToPermissionResponse(newPermission);
   }

   @Override
   public PermissionResponse deletePermissionById(Long permissionId) {
      GrantedPermission permission = permissionRepository.findById(permissionId)
         .orElseThrow(() -> new ObjectNotFoundException("Permission not found. Permission: " + permissionId));

      permissionRepository.delete(permission);

      return this.entityToPermissionResponse(permission);
   }

   private PermissionResponse entityToPermissionResponse(GrantedPermission grantedPermission) {
      if(grantedPermission == null) return null;

      return PermissionResponse.builder()
         .permissionId(grantedPermission.getGrantedPermissionId())
         .role(grantedPermission.getRole().getName())
         .operation(grantedPermission.getOperation().getName())
         .httpMethod(grantedPermission.getOperation().getHttpMethod())
         .module(grantedPermission.getOperation().getModule().getName())
         .build();
   }
}
