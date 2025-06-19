package com.technologyos.auth.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Setter
@Getter
@ToString
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

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      GrantedPermission that = (GrantedPermission) o;
      return Objects.equals(grantedPermissionId, that.grantedPermissionId);
   }

   @Override
   public int hashCode() {
      return Objects.hashCode(grantedPermissionId);
   }
}
