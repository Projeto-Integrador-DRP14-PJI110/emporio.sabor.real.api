package org.emporio.sabor.real.api.exception;

public class BadRequestExcpetion extends RuntimeException {

      public BadRequestExcpetion(String message) {
          super(message);
      }

      public BadRequestExcpetion(String message, Throwable cause) {
          super(message, cause);
      }

}
