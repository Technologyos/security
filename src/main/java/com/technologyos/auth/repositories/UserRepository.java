package com.technologyos.auth.repositories;

import com.technologyos.auth.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
   Optional<User> findByUsername(String username);

   Optional<User> findByEmail(String email);

   List<User> findByStatusId(Long statusId);
}
