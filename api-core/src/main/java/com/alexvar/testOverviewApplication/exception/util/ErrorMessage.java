package com.alexvar.testOverviewApplication.exception.util;

import lombok.Builder;
import lombok.Value;

/**
 * Model representation that stores detailed information about
 * error that was occurred by making requests to resources.
 *
 * @since 1.0
 * @author AlexVar
 */
@Builder
@Value
public class ErrorMessage {

  int status;

  String description;
}
