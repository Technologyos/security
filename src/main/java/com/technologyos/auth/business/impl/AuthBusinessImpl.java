package com.technologyos.auth.business.impl;

import com.technologyos.auth.business.AuthBusiness;
import com.technologyos.auth.dtos.auth.AuthenticationRequest;
import com.technologyos.auth.dtos.auth.AuthenticationResponse;
import com.technologyos.auth.dtos.signup.RegisteredUser;
import com.technologyos.auth.dtos.signup.UserRequest;
import com.technologyos.auth.entities.JwtToken;
import com.technologyos.auth.entities.User;

import com.technologyos.auth.services.JwtService;
import com.technologyos.auth.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j
public class AuthBusinessImpl implements AuthBusiness {
   private final AuthenticationManager authenticationManager;
   private final UserService userService;
   private final JwtService jwtService;

   @Override
   public RegisteredUser registerCustomer(UserRequest newUser) {
      User user = userService.registerCustomer(newUser);
      String jwt = jwtService.generateToken(user, generateExtraClaims(user));
      saveUserToken(user, jwt);
      return RegisteredUser.builder()
         .userId(user.getUserId())
         .name(user.getName())
         .email(user.getEmail())
         .username(user.getUsername())
         .role(user.getRole().getName())
         .jwt(jwt)
         .build();
   }

   private Map<String, Object> generateExtraClaims(User user) {
      Map<String, Object> extraClaims = new HashMap<>();
      extraClaims.put("name", user.getName());
      extraClaims.put("role", user.getRole().getName());
      extraClaims.put("authorities", user.getAuthorities());
      return extraClaims;
   }

   @Override
   public AuthenticationResponse login(AuthenticationRequest authRequest) {
      Authentication authentication = new UsernamePasswordAuthenticationToken(
         authRequest.getEmail(), authRequest.getPassword()
      );

      authenticationManager.authenticate(authentication);

      User user = userService.findCustomerByEmail(authRequest.getEmail());

      String jwt = jwtService.generateToken(user, generateExtraClaims(user));
      saveUserToken(user, jwt);

      return AuthenticationResponse.builder()
         .username(user.getUsername())
         .email(user.getEmail())
         .name(user.getName())
         .role(user.getRole().getName())
         .jwt(jwt)
         .build();
   }

   private void saveUserToken(User user, String jwt) {
      JwtToken jwtToken = JwtToken.builder()
         .token(jwt)
         .user(user)
         .expiration(jwtService.extractExpiration(jwt))
         .isValid(true)
         .build();
      jwtService.saveToken(jwtToken);
   }

   @Override
   public boolean validateToken(String jwt) {
      try{
         JwtToken token = jwtService.findByToken(jwt);

         if (!token.isValid() || token.getExpiration().before(new Date())) {
            return false;
         }

         jwtService.extractUsername(token.getToken());
         return true;
      }catch (Exception e){
         log.warn("Invalid JWT: {}", e.getMessage());
         return false;
      }
   }

   @Override
   public User getProfile() {
      UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
      String email = (String) auth.getPrincipal();

      return userService.findCustomerByEmail(email);
   }

   @Override
   public void logout(HttpServletRequest request) {
      String jwt = jwtService.extractJwtFromRequest(request);

      if(!StringUtils.hasText(jwt)) return;

      JwtToken token = jwtService.findByToken(jwt);

      if(token.isValid()){
         token.setValid(false);
         jwtService.saveToken(token);
      }
   }
}
