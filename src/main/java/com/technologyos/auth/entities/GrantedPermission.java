package com.technologyos.auth.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GrantedPermission {
   @EmbeddedId
   private GrantedPermissionId grantedPermissionId;

   @ManyToOne
   @MapsId("roleId")
   @JoinColumn(name = "role_id")
   private Role role;

   @ManyToOne
   @MapsId("operationId")
   @JoinColumn(name = "operation_id")
   private Operation operation;
}
