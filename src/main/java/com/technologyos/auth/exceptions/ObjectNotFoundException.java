package com.technologyos.auth.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
public class ObjectNotFoundException extends RuntimeException {
   private Long id;
   private int code;
   private String message;
   private HttpStatus httpStatus;
   private Throwable cause;

   public ObjectNotFoundException(int code, String message, HttpStatus httpStatus) {
      this.code = code;
      this.message = message;
      this.httpStatus = httpStatus;
      this.cause = null;
   }

   public ObjectNotFoundException(String message, Throwable cause) {
      this.message = message;
      this.cause = cause;
   }

   @Override
   public String getMessage(){
      String message = super.getMessage() == null ? "": super.getMessage();
      return message
         .concat("( object not found: ")
         .concat(this.message)
         .concat(")");
   }
}
