package com.technologyos.security.repositories;

import com.technologyos.security.entities.JwtToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JwtTokenRepository extends JpaRepository<JwtToken, Long> {
   Optional<JwtToken> findByToken(String jwt);
}
