package com.alexvar.testOverviewApplication.exception.util;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Holder for containing special exception messages to provide
 * information in response form services.
 * <p>
 *   Exception messages stores in special `*.properties` file
 *   that specified in {@link PropertySource} annotation.
 * </p>
 *
 * @since 1.0
 * @author AlexVar
 */
@Component
@Getter
@PropertySource("classpath:exception-messages.properties")
public class ExceptionMessage {

  @Value("${not.found.error.message}")
  private String notFoundExceptionMessage;

  @Value("${bad.request.error.message}")
  private String badRequestExceptionMessage;
}
