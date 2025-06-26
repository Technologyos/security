package com.technologyos.auth.controllers;

import com.technologyos.auth.business.AuthBusiness;
import com.technologyos.auth.dtos.signup.RegisteredUser;
import com.technologyos.auth.dtos.signup.UserRequest;
import com.technologyos.auth.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/customers")
public class CustomerController {
   private final AuthBusiness authBusiness;
   private final UserService userService;

   @GetMapping
   public ResponseEntity<Page<RegisteredUser>> findAll(Pageable pageable){
      Page<RegisteredUser> customers = userService.findAll(pageable);

      if(customers.hasContent()){
         return ResponseEntity.ok(customers);
      }

      return ResponseEntity.notFound().build();
   }

   @PostMapping
   public ResponseEntity<RegisteredUser> registerCustomer(@RequestBody @Valid UserRequest userRequest){
      RegisteredUser registeredUser = authBusiness.registerCustomer(userRequest);
      return ResponseEntity
         .status(HttpStatus.CREATED)
         .body(registeredUser);
   }
}
