package com.technologyos.auth.dtos.role;

import com.technologyos.auth.dtos.status.StatusEnum;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleRequest {
   @NotBlank
   private String name;
   private StatusEnum status;
}
