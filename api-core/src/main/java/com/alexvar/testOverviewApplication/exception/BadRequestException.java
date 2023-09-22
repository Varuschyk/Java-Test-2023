package com.alexvar.testOverviewApplication.exception;

import jakarta.annotation.Nonnull;
import java.io.Serial;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception that responses 400 status when request to resource is not valid.
 *
 * @since 1.0
 * @author AlexVar
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

  @Serial
  private static final long serialVersionUID = -296598092263396379L;

  public BadRequestException(@Nonnull final String message) {
    super(message);
  }
}
