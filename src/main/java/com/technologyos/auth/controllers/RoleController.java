package com.technologyos.auth.controllers;

import com.technologyos.auth.dtos.role.RoleRequest;
import com.technologyos.auth.dtos.status.StatusEnum;
import com.technologyos.auth.entities.Role;
import com.technologyos.auth.services.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Tag(name = "role resources", description = "This APi serve all functionality for management roles")
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/roles")
public class RoleController {
   private final RoleService roleService;

   @Operation(summary = "get roles")
   @GetMapping()
   public ResponseEntity<?> roles(Pageable pageable){
      log.info("Get: roles ");
      return ResponseEntity.ok(this.roleService.findAll(pageable));
   }

   @Operation(summary = "get a role given a role id")
   @GetMapping("/{roleId}")
   public ResponseEntity<?> roleById(@PathVariable(name = "roleId") Long roleId){
      log.info("Get: role {}", roleId);
      return ResponseEntity.ok(this.roleService.findRoleById(roleId));
   }

   @Operation(summary = "create a role")
   @PostMapping()
   public ResponseEntity<?> createRole(@RequestBody RoleRequest roleRequest, HttpServletRequest request){
      log.info("create: role {}", roleRequest.getName());
      Role newStatus = this.roleService.createRole(roleRequest);

      String baseUrl = request.getRequestURI();
      URI newLocation = URI.create(baseUrl + "/"+ newStatus.getStatusId());

      return ResponseEntity.created(newLocation).body(newStatus);
   }

   @Operation(summary = "update a role by role id")
   @PutMapping("/{roleId}")
   public ResponseEntity<?> updateRole(@PathVariable(name = "roleId") Long roleId,
                                       @RequestBody RoleRequest roleRequest){
      log.info("updating: role {}", roleId);
      return ResponseEntity.ok(this.roleService.updateRole(roleRequest, roleId));
   }

   @Operation(summary = "enabled role")
   @PutMapping("/{roleId}/enabled")
   public ResponseEntity<?> enableRole(@PathVariable(name = "roleId") Long roleId){
      log.info("enabled role {}", roleId);
      return ResponseEntity.ok(roleService.enableById(roleId));
   }

   @Operation(summary = "disabled role")
   @PutMapping("/{roleId}/disabled")
   public ResponseEntity<?> disableRole(@PathVariable(name = "roleId") Long roleId){
      log.info("disable role {}", roleId);
      return ResponseEntity.ok(roleService.disableById(roleId));
   }

   @Operation(summary = "get roles by status id")
   @GetMapping("/filter")
   public ResponseEntity<?> findByStatusId(@RequestParam(name = "statusId") StatusEnum statusId){
      return ResponseEntity.ok(this.roleService.findByStatusId(statusId.getCode()));
   }
}
