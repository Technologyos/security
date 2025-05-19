package com.technologyos.security.dto.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest implements Serializable {
   @NotBlank
   private String username;
   @NotBlank
   private String password;
}
