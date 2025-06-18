package com.technologyos.auth.business;

import com.technologyos.auth.dtos.auth.AuthenticationRequest;
import com.technologyos.auth.dtos.auth.AuthenticationResponse;
import com.technologyos.auth.dtos.signup.ProfileResponse;
import com.technologyos.auth.dtos.signup.RegisteredUser;
import com.technologyos.auth.dtos.signup.UserRequest;
import jakarta.servlet.http.HttpServletRequest;

public interface AuthBusiness {

   RegisteredUser registerCustomer(UserRequest newUser);

   AuthenticationResponse login(AuthenticationRequest authRequest);

   boolean validateToken(String jwt);

   ProfileResponse findProfile();

   void logout(HttpServletRequest request);
}
