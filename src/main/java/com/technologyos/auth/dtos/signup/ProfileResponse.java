package com.technologyos.auth.dtos.signup;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileResponse {
   private Long userId;
   private String username;
   private String email;
   private String name;
   private String role;
   private Boolean enabled;
   private Collection<? extends GrantedAuthority> authorities;
}
