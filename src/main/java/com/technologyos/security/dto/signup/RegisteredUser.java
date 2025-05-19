package com.technologyos.security.dto.signup;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisteredUser implements Serializable {
   private Long userId;
   private String username;
   private String name;
   private String role;
   private String jwt;
}
