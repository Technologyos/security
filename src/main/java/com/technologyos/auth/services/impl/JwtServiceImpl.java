package com.technologyos.auth.services.impl;

import com.technologyos.auth.entities.JwtToken;
import com.technologyos.auth.entities.User;
import com.technologyos.auth.exceptions.ObjectNotFoundException;
import com.technologyos.auth.repositories.JwtTokenRepository;
import com.technologyos.auth.services.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Service
@Slf4j
public class JwtServiceImpl implements JwtService {
   @Autowired
   private JwtTokenRepository jwtTokenRepository;

   @Value("${security.jwt.expiration-in-minutes}")
   private Long expirationInMinutes;

   @Value("${security.jwt.secret-key}")
   private String secretKey;

   @Override
   public JwtToken findByToken(String jwt) {
      return jwtTokenRepository.findByToken(jwt)
         .orElseThrow(() -> new ObjectNotFoundException("Token not found: " + jwt));
   }

   @Override
   public String generateToken(User user, Map<String, Object> extraClaims) {
      Date issuedAt = new Date(System.currentTimeMillis());
      Date expiration = new Date( (expirationInMinutes * 60 * 1000) + issuedAt.getTime());
      return Jwts.builder()
         .header()
         .type("JWT")
         .and()
         .subject(user.getEmail())
         .issuedAt(issuedAt)
         .expiration(expiration)
         .claims(extraClaims)
         .signWith(generateKey(), Jwts.SIG.HS256)
         .compact();
   }

   private SecretKey generateKey(){
      byte[] passwordDecoded = Decoders.BASE64.decode(secretKey);
      return Keys.hmacShaKeyFor(passwordDecoded);
   }

   @Override
   public void saveToken(JwtToken token) {
      jwtTokenRepository.save(token);
   }

   @Override
   public String extractUsername(String jwt) {
      return extractAllClaims(jwt).getSubject();
   }

   @Override
   public Date extractExpiration(String jwt) {
      return extractAllClaims(jwt).getExpiration();
   }

   private Claims extractAllClaims(String jwt) {
      return Jwts.parser()
         .verifyWith(generateKey())
         .build()
         .parseSignedClaims(jwt)
         .getPayload();
   }

   @Override
   public String extractJwtFromRequest(HttpServletRequest request) {
      String authorizationHeader = request.getHeader("Authorization");
      if(!StringUtils.hasText(authorizationHeader) || !authorizationHeader.startsWith("Bearer ")){
         return null;
      }
      return authorizationHeader.split(" ")[1];
   }
}
