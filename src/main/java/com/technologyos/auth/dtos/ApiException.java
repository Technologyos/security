package com.technologyos.auth.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiException implements Serializable {
   @Schema(description = "The unique error code", name = "code", example = "200")
   private int code;
   private String backendMessage;
   @Schema(description = "A human-readable explanation of the error", name = "message",
      example = "The user does not have the properly permissions to access the "
         + "resource, please contact with ")
   private String message;
   private String url;
   private String method;
   @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
   private LocalDateTime timestamp;
}
