package com.technologyos.auth.exceptions;

public class ObjectNotFoundException extends RuntimeException {
   public ObjectNotFoundException() {}

   public ObjectNotFoundException(String message) {
      super(message);
   }

   public ObjectNotFoundException(String message, Throwable cause) {
      super(message, cause);
   }
}
