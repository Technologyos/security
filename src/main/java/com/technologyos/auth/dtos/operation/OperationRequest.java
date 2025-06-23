package com.technologyos.auth.dtos.operation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperationRequest {
   @NotBlank
   private String name;
   private String path;
   @NotBlank
   private String httpMethod;
   @NotNull
   private Boolean permitAll;
   @NotNull
   private Long moduleId;
   private Long statusId;
}
