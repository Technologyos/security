package com.technologyos.security.services;

import com.technologyos.security.dto.signup.RegisteredUser;
import com.technologyos.security.dto.signup.UserRequest;
import com.technologyos.security.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {

   Page<RegisteredUser> findAll(Pageable pageable);

   User registerCustomer(UserRequest userRequest);

   Optional<User> findCustomerByUsername(String username);
}
