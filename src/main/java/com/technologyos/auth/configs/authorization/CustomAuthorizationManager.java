package com.technologyos.auth.configs.authorization;

import com.technologyos.auth.entities.GrantedPermission;
import com.technologyos.auth.entities.Operation;
import com.technologyos.auth.entities.User;
import com.technologyos.auth.repositories.OperationRepository;
import com.technologyos.auth.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Custom authorization manager that verifies if a user has access to an endpoint.
 */
@Component
@AllArgsConstructor
public class CustomAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {
   private final OperationRepository operationRepository;
   private final UserService userService;

   @Override
   public AuthorizationDecision check(Supplier<Authentication> authentication,
                                      RequestAuthorizationContext requestContext) {
      HttpServletRequest request = requestContext.getRequest();

      String url = extractUrl(request);
      String httpMethod = request.getMethod();

      if(isPublic(url, httpMethod)){
         return new AuthorizationDecision(true);
      }

      boolean isGranted = isGranted(url, httpMethod, authentication.get());

      return new AuthorizationDecision(isGranted);
   }

   private boolean isGranted(String url, String httpMethod, Authentication authentication) {
      if(!(authentication instanceof UsernamePasswordAuthenticationToken)){
         throw new AuthenticationCredentialsNotFoundException("User not logged in");
      }

      List<Operation> operations = obtainOperations(authentication);

      return operations.stream()
         .anyMatch(getOperationPredicate(url, httpMethod));
   }

   private List<Operation> obtainOperations(Authentication authentication) {
      UsernamePasswordAuthenticationToken authToken = (UsernamePasswordAuthenticationToken) authentication;
      String email = (String) authToken.getPrincipal();
      User user = userService.findCustomerByEmail(email);

      return user.getRole().getPermissions().stream()
         .map(GrantedPermission::getOperation)
         .collect(Collectors.toList());
   }

   private boolean isPublic(String url, String httpMethod) {
      List<Operation> publicAccessEndpoints = operationRepository
         .findByPublicAccess();

      return publicAccessEndpoints.stream()
         .anyMatch(getOperationPredicate(url, httpMethod));
   }

   private static Predicate<Operation> getOperationPredicate(String url, String httpMethod) {
      return operation -> {
         String fullPath  = operation.getModule().getBasePath();
         Pattern pattern = Pattern.compile(fullPath.concat(operation.getPath()));
         Matcher matcher = pattern.matcher(url);
         return matcher.matches() && operation.getHttpMethod().equals(httpMethod);
      };
   }

   private String extractUrl(HttpServletRequest request) {
      return request.getRequestURI().replace(request.getContextPath(), "");
   }
}
