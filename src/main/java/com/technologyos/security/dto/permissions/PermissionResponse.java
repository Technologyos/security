package com.technologyos.security.dto.permissions;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PermissionResponse implements Serializable {
   private long permissionId;
   private String operation;
   private String httpMethod;
   private String module;
   private String role;
}
