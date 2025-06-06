package com.technologyos.auth.dtos.auth;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationResponse implements Serializable {
   private String username;
   private String email;
   private String name;
   private String role;
   private String jwt;
}
