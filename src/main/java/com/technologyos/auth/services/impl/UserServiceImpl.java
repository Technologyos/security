package com.technologyos.auth.services.impl;

import com.technologyos.auth.dtos.signup.RegisteredUser;
import com.technologyos.auth.dtos.signup.UserRequest;
import com.technologyos.auth.dtos.status.StatusEnum;
import com.technologyos.auth.entities.Role;
import com.technologyos.auth.entities.User;
import com.technologyos.auth.exceptions.InvalidPasswordException;
import com.technologyos.auth.exceptions.ObjectNotFoundException;
import com.technologyos.auth.repositories.UserRepository;
import com.technologyos.auth.services.RoleService;
import com.technologyos.auth.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
   private final UserRepository userRepository;
   private final PasswordEncoder passwordEncoder;
   private final RoleService roleService;

   @Override
   public Page<RegisteredUser> findAll(Pageable pageable) {
      return userRepository.findAll(pageable)
         .map(this::userEntityToRegisteredUser);
   }

   private RegisteredUser userEntityToRegisteredUser(User user){
      if(user == null) return null;

      return RegisteredUser.builder()
         .userId(user.getUserId())
         .username(user.getUsername())
         .email(user.getEmail())
         .name(user.getName())
         .role(user.getRole().getName())
         .status(user.getStatus().getName())
         .build();
   }

   @Override
   public User registerCustomer(UserRequest userRequest) {
      validatePassword(userRequest);
      User user = new User();
      user.setUsername(userRequest.getUsername());
      user.setEmail(userRequest.getEmail());
      user.setName(userRequest.getName());
      user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
      Role defaultRole = roleService.findDefaultRole();
      user.setRole(defaultRole);
      user.setUserId(StatusEnum.ENABLED.getCode());
      return userRepository.save(user);
   }

   @Override
   public User findCustomerByEmail(String email) {
      return userRepository.findByEmail(email)
         .orElseThrow(() -> new ObjectNotFoundException(HttpStatus.NOT_FOUND.value(),
            "customer not found by email: " + email, HttpStatus.NOT_FOUND));
   }

   @Override
   public User findCustomerByUsername(String username) {
      return userRepository.findByUsername(username)
         .orElseThrow(() -> new ObjectNotFoundException(HttpStatus.NOT_FOUND.value(),
            "customer not found by username: " + username, HttpStatus.NOT_FOUND));
   }

   @Override
   public User findCustomerById(Long userId) {
      return userRepository.findById(userId)
         .orElseThrow(() -> new ObjectNotFoundException(HttpStatus.NOT_FOUND.value(),
            "customer not found by id: " + userId, HttpStatus.NOT_FOUND));
   }

   @Override
   public User disableById(Long userId) {
      User user = this.findCustomerById(userId);
      user.setStatusId(StatusEnum.DISABLED.getCode());
      return userRepository.save(user);
   }

   @Override
   public User enableById(Long userId) {
      User user = this.findCustomerById(userId);
      user.setStatusId(StatusEnum.ENABLED.getCode());
      return userRepository.save(user);
   }

   @Override
   public List<User> findByStatusId(Long statusId) {
      return userRepository.findByStatusId(statusId);
   }

   private void validatePassword(UserRequest userRequest) {
      String password = userRequest.getPassword();
      String repeatedPassword = userRequest.getRepeatedPassword();

      List<Map.Entry<Boolean, String>> rules = List.of(
         Map.entry(!StringUtils.hasText(password), "Password must not be empty"),
         Map.entry(!StringUtils.hasText(repeatedPassword), "Repeated password must not be empty"),
         Map.entry(!password.equals(repeatedPassword), "Passwords do not match"),
         Map.entry(password.length() < 8, "Password must be at least 8 characters long"),
         Map.entry(!password.matches(".*[A-Z].*"), "Password must contain at least one uppercase letter"),
         Map.entry(!password.matches(".*[a-z].*"), "Password must contain at least one lowercase letter"),
         Map.entry(!password.matches(".*\\d.*"), "Password must contain at least one number"),
         Map.entry(!password.matches(".*[!@#$%^&*()_+\\-\\[\\]{}|;':\",.<>/?].*"), "Password must contain at least one special character")
      );

      rules.stream()
         .filter(Map.Entry::getKey)
         .findFirst()
         .ifPresent(rule -> {
            throw new InvalidPasswordException(rule.getValue());
         });
   }
}
