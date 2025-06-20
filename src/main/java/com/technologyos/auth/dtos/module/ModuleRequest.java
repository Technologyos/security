package com.technologyos.auth.dtos.module;

import com.technologyos.auth.dtos.status.StatusEnum;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ModuleRequest {
   @NotBlank
   private String name;
   @NotBlank
   private String basePath;
   private StatusEnum status;
}
