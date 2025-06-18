package com.technologyos.auth.entities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GrantedPermissionId implements Serializable {
   private Long roleId;
   private Long operationId;
}
