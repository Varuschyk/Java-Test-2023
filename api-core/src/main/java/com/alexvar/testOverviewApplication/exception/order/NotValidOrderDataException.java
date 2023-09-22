package com.alexvar.testOverviewApplication.exception.order;

import com.alexvar.testOverviewApplication.exception.BadRequestException;
import jakarta.annotation.Nonnull;
import java.io.Serial;


/**
 * Exception of {@link BadRequestException} type
 * that corresponds to {@link com.alexvar.testOverviewApplication.persistence.entity.Order} entity.
 *
 * @since 1.0
 * @author AlexVar
 */
public class NotValidOrderDataException extends BadRequestException {

  @Serial
  private static final long serialVersionUID = -8230750312895215676L;

  public NotValidOrderDataException(@Nonnull final String message) {
    super(message);
  }
}
