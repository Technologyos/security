package com.technologyos.auth.business;

import com.technologyos.auth.dtos.auth.AuthenticationRequest;
import com.technologyos.auth.dtos.auth.AuthenticationResponse;
import com.technologyos.auth.dtos.signup.RegisteredUser;
import com.technologyos.auth.dtos.signup.UserRequest;
import com.technologyos.auth.entities.User;
import jakarta.servlet.http.HttpServletRequest;

public interface AuthBusiness {

   RegisteredUser registerCustomer(UserRequest newUser);

   AuthenticationResponse login(AuthenticationRequest authRequest);

   boolean validateToken(String jwt);

   User getProfile();

   void logout(HttpServletRequest request);
}
