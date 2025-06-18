package com.technologyos.auth.controllers;

import com.technologyos.auth.business.AuthBusiness;
import com.technologyos.auth.dtos.auth.AuthenticationRequest;
import com.technologyos.auth.dtos.auth.AuthenticationResponse;
import com.technologyos.auth.dtos.auth.LogoutResponse;
import com.technologyos.auth.dtos.signup.ProfileResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
   private final AuthBusiness authBusiness;

   @PostMapping("/authenticate")
   public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody @Valid AuthenticationRequest authenticationRequest){
      return ResponseEntity.ok(authBusiness.login(authenticationRequest));
   }

   @PostMapping("/logout")
   public ResponseEntity<LogoutResponse> logout(HttpServletRequest request){
      authBusiness.logout(request);
      return ResponseEntity.ok(new LogoutResponse("Logout success"));
   }

   @GetMapping("/validate-token")
   public ResponseEntity<Boolean> validate(@RequestParam("jwt") String jwt){
      return ResponseEntity.ok(authBusiness.validateToken(jwt));
   }

   @GetMapping("/profile")
   public ResponseEntity<ProfileResponse> findProfile(){
      return ResponseEntity.ok(authBusiness.findProfile());
   }
}
