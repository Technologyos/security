package com.technologyos.auth.exceptions;

import com.technologyos.auth.dtos.ApiException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

   @ExceptionHandler(Exception.class)
   public ResponseEntity<?> handlerGenericException(HttpServletRequest request, Exception exception){
      ApiException apiError = ApiException.builder()
         .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
         .backendMessage(exception.getLocalizedMessage())
         .url(request.getRequestURL().toString())
         .method(request.getMethod())
         .message("Error interno en el servidor, vuelva a intentarlo")
         .timestamp(LocalDateTime.now())
         .build();
      log.info("GenericException {} ", apiError);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError);
   }

   @ExceptionHandler(AccessDeniedException.class)
   public ResponseEntity<?> handlerAccessDeniedException(HttpServletRequest request, AccessDeniedException exception){
      ApiException apiError = ApiException.builder()
         .code(HttpStatus.FORBIDDEN.value())
         .backendMessage(exception.getLocalizedMessage())
         .url(request.getRequestURL().toString())
         .method(request.getMethod())
         .message("Acceso denegado. No tienes los permisos necesarios para acceder a esta función. " +
            "Por favor, contacta al administrador si crees que esto es un error.")
         .timestamp(LocalDateTime.now())
         .build();
      log.info("AccessDeniedException {} ", apiError);
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body(apiError);
   }

   @ExceptionHandler(MethodArgumentNotValidException.class)
   public ResponseEntity<?> handlerMethodArgumentNotValidException(HttpServletRequest request,
                                                                   MethodArgumentNotValidException exception){
      ApiException apiError = ApiException.builder()
         .code(HttpStatus.BAD_REQUEST.value())
         .backendMessage(exception.getLocalizedMessage())
         .url(request.getRequestURL().toString())
         .method(request.getMethod())
         .message("Error en la petición enviada")
         .timestamp(LocalDateTime.now())
         .build();
      log.info("MethodArgumentNotValidException {} ", apiError);
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
   }
}
