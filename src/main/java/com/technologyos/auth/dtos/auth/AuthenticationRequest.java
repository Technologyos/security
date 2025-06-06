package com.technologyos.auth.dtos.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest implements Serializable {
   @NotBlank(message = "Email is required")
   private String email;

   @NotBlank(message = "Password is required")
   private String password;
}
