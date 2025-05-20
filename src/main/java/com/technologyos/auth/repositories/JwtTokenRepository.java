package com.technologyos.auth.repositories;

import com.technologyos.auth.entities.JwtToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JwtTokenRepository extends JpaRepository<JwtToken, Long> {
   Optional<JwtToken> findByToken(String jwt);
}
