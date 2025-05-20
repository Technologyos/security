package com.technologyos.security.services;

import com.technologyos.security.dto.permissions.PermissionRequest;
import com.technologyos.security.dto.permissions.PermissionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PermissionService {
   Page<PermissionResponse> findAll(Pageable pageable);

   Optional<PermissionResponse> findPermissionById(Long permissionId);

   PermissionResponse createPermission(PermissionRequest permissionRequest);

   PermissionResponse deletePermissionById(Long permissionId);
}
