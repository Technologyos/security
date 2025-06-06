package com.technologyos.auth.controllers;

import com.technologyos.auth.business.AuthBusiness;
import com.technologyos.auth.dtos.auth.AuthenticationRequest;
import com.technologyos.auth.dtos.auth.AuthenticationResponse;
import com.technologyos.auth.dtos.auth.LogoutResponse;
import com.technologyos.auth.entities.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

   @Autowired
   private AuthBusiness authBusiness;

   @GetMapping("/validate-token")
   public ResponseEntity<Boolean> validate(@RequestParam("jwt") String jwt){
      boolean isTokenValid = authBusiness.validateToken(jwt);
      return ResponseEntity.ok(isTokenValid);
   }

   @PostMapping("/authenticate")
   public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody @Valid AuthenticationRequest authenticationRequest){
      AuthenticationResponse rsp = authBusiness.login(authenticationRequest);
      return ResponseEntity.ok(rsp);
   }

   @PostMapping("/logout")
   public ResponseEntity<LogoutResponse> logout(HttpServletRequest request){
      authBusiness.logout(request);
      return ResponseEntity.ok(new LogoutResponse("Logout success"));
   }

   @GetMapping("profile")
   public ResponseEntity<User> getProfile(){
      return ResponseEntity.ok(authBusiness.getProfile());
   }
}
