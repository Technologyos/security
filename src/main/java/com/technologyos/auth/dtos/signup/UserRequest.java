package com.technologyos.auth.dtos.signup;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserRequest implements Serializable {
   @Size(min = 10)
   @NotBlank
   private String name;

   @NotBlank
   private String username;

   @NotBlank
   @Pattern(
      regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$",
      message = "El correo debe tener un formato válido"
   )
   private String email;

   @Size(min = 9)
   @NotBlank
   private String password;

   @Size(min = 9)
   @NotBlank
   private String repeatedPassword;
}
