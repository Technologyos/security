package com.technologyos.auth.services;

import com.technologyos.auth.entities.JwtToken;
import com.technologyos.auth.entities.User;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Date;
import java.util.Map;

public interface JwtService {
   JwtToken findByToken(String jwt);

   String generateToken(User user, Map<String, Object> extraClaims);

   void saveToken(JwtToken token);

   String extractUsername(String jwt);

   Date extractExpiration(String jwt);

   String extractJwtFromRequest(HttpServletRequest request);
}
