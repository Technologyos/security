package com.technologyos.security.controllers;

import com.technologyos.security.dto.auth.AuthenticationRequest;
import com.technologyos.security.dto.auth.AuthenticationResponse;
import com.technologyos.security.dto.auth.LogoutResponse;
import com.technologyos.security.entities.User;
import com.technologyos.security.services.auth.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

   @Autowired
   private AuthenticationService authenticationService;

   @GetMapping("/validate-token")
   public ResponseEntity<Boolean> validate(@RequestParam("jwt") String jwt){
      boolean isTokenValid = authenticationService.validateToken(jwt);
      return ResponseEntity.ok(isTokenValid);
   }

   @PostMapping("/authenticate")
   public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody @Valid AuthenticationRequest authenticationRequest){
      AuthenticationResponse rsp = authenticationService.login(authenticationRequest);

      return ResponseEntity.ok(rsp);
   }

   @PostMapping("/logout")
   public ResponseEntity<LogoutResponse> logout(HttpServletRequest request){
      authenticationService.logout(request);
      return ResponseEntity.ok(new LogoutResponse("Logout success"));
   }

   @GetMapping("profile")
   public ResponseEntity<User> findMyProfile(){
      User user = authenticationService.findLoggedInUser();
      return ResponseEntity.ok(user);
   }
}
