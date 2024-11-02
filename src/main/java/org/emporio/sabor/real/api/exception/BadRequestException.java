package org.emporio.sabor.real.api.exception;

public class BadRequestException extends RuntimeException {

      public BadRequestException(String message) {
          super(message);
      }

      public BadRequestException(String message, Throwable cause) {
          super(message, cause);
      }

}