package com.technologyos.auth.services;

import com.technologyos.auth.dtos.signup.RegisteredUser;
import com.technologyos.auth.dtos.signup.UserRequest;
import com.technologyos.auth.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

   Page<RegisteredUser> findAll(Pageable pageable);

   User registerCustomer(UserRequest userRequest);

   User findCustomerByEmail(String email);

   User findCustomerByUsername(String username);

   User findCustomerById(Long userId);

   User disableById(Long userId);

   User enableById(Long userId);

   List<User> findByStatusId(Long statusId);
}
