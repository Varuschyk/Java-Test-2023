package com.alexvar.testOverviewApplication.config.handler;

import com.alexvar.testOverviewApplication.exception.BadRequestException;
import com.alexvar.testOverviewApplication.exception.NotFoundException;
import com.alexvar.testOverviewApplication.exception.util.ExceptionMessage;
import com.alexvar.testOverviewApplication.exception.util.ExceptionWebSupport;
import com.alexvar.testOverviewApplication.exception.util.ErrorMessage;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Main exception handler that has inner handlers to catch all errors by http status.
 * <p>
 *   Each inner handler catches each error by his error status, and providing special
 *   error message from {@link ExceptionMessage}.
 *   For simplify, is using {@link ExceptionWebSupport} that provides simple methods,
 *   that allows get needed information fast and convenient.
 * </p>
 *
 * @see ExceptionMessage
 * @see ExceptionWebSupport
 * @since 1.0
 * @author AlexVar
 */
@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

  private final ExceptionMessage exceptionMessage;
  private final ExceptionWebSupport exceptionWebSupport;

  /**
   * Handles {@link NotFoundException} when desired resources wasn't found.
   *
   * @param notFoundException occurred exception of {@link NotFoundException} type.
   * @return {@link ResponseEntity<ErrorMessage>} information of exception that will display in JSON format.
   */
  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ErrorMessage> handleNotFoundException(@Nonnull final NotFoundException notFoundException) {
    log.error("Exception was thrown from not found resource.", notFoundException);
    final var responseMessage = exceptionMessage.getNotFoundExceptionMessage();
    return exceptionWebSupport.getErrorResponse(notFoundException, HttpStatus.NOT_FOUND, responseMessage);
  }

  /**
   * Handles {@link BadRequestException} when desired resources wasn't found.
   *
   * @param badRequestException occurred exception of {@link BadRequestException} type.
   * @return {@link ResponseEntity<ErrorMessage>} information of exception that will display in JSON format.
   */
  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<ErrorMessage> handleBadRequestException(@Nonnull final BadRequestException badRequestException) {
    log.info("Exception was thrown by bad request.", badRequestException);
    final var responseMessage = exceptionMessage.getBadRequestExceptionMessage();
    return exceptionWebSupport.getErrorResponse(badRequestException, HttpStatus.BAD_REQUEST, responseMessage);
  }
}
