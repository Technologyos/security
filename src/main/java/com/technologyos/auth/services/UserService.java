package com.technologyos.auth.services;

import com.technologyos.auth.dtos.signup.RegisteredUser;
import com.technologyos.auth.dtos.signup.UserRequest;
import com.technologyos.auth.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {

   Page<RegisteredUser> findAll(Pageable pageable);

   User registerCustomer(UserRequest userRequest);

   Optional<User> findCustomerByUsername(String username);
}
