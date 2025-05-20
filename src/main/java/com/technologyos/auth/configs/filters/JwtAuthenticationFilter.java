package com.technologyos.auth.configs.filters;

import com.technologyos.auth.entities.JwtToken;
import com.technologyos.auth.exceptions.ObjectNotFoundException;
import com.technologyos.auth.repositories.JwtTokenRepository;
import com.technologyos.auth.services.UserService;
import com.technologyos.auth.security.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

@Component
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
   private final JwtService jwtService;
   private final UserService userService;
   private final JwtTokenRepository jwtTokenRepository;

   @Override
   protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                   FilterChain filterChain) throws ServletException, IOException {
      String jwt = jwtService.extractJwtFromRequest(request);

      if(!StringUtils.hasText(jwt)){
         filterChain.doFilter(request, response);
         return;
      }

      Optional<JwtToken> token = jwtTokenRepository.findByToken(jwt);
      boolean isValid = validateToken(token);

      if(!isValid){
         filterChain.doFilter(request, response);
         return;
      }
      String username = jwtService.extractUsername(jwt);

      UserDetails user = userService.findCustomerByUsername(username)
         .orElseThrow(() -> new ObjectNotFoundException("User not found. username: " + username));

      UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
         username, null, user.getAuthorities()
      );

      authToken.setDetails(new WebAuthenticationDetails(request));
      SecurityContextHolder.getContext().setAuthentication(authToken);

      filterChain.doFilter(request, response);
   }

   private boolean validateToken(Optional<JwtToken> optionalToken) {
      if(optionalToken.isEmpty()){
         return false;
      }

      JwtToken token = optionalToken.get();
      Date now = new Date(System.currentTimeMillis());
      boolean isValid = token.isValid() && token.getExpiration().after(now);

      if(!isValid){
         updateTokenStatus(token);
      }

      return isValid;
   }

   private void updateTokenStatus(JwtToken token) {
      token.setValid(false);
      jwtTokenRepository.save(token);
   }
}
