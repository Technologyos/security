package com.technologyos.auth.dtos.operation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperationRequest {
   private String name;
   private String path;
   private String httpMethod;
   private boolean permitAll;
   private Long moduleId;
}
