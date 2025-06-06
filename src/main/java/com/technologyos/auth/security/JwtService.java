package com.technologyos.auth.security;

import com.technologyos.auth.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {

   @Value("${security.jwt.expiration-in-minutes}")
   private Long expirationInMinutes;

   @Value("${security.jwt.secret-key}")
   private String secretKey;

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

   public String extractUsername(String jwt) {
      return extractAllClaims(jwt).getSubject();
   }

   private Claims extractAllClaims(String jwt) {
      return Jwts.parser().verifyWith( generateKey() ).build()
         .parseSignedClaims(jwt).getPayload();
   }

   public String extractJwtFromRequest(HttpServletRequest request) {
      String authorizationHeader = request.getHeader("Authorization");
      if(!StringUtils.hasText(authorizationHeader) || !authorizationHeader.startsWith("Bearer ")){
         return null;
      }
      return authorizationHeader.split(" ")[1];
   }

   public Date extractExpiration(String jwt) {
      return extractAllClaims(jwt).getExpiration();
   }
}
