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
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(nullable = false)
   private Long grantedPermissionId;

   @ManyToOne
   @JoinColumn(name = "role_id")
   private Role role;

   @ManyToOne
   @JoinColumn(name = "operation_id")
   private Operation operation;
}
