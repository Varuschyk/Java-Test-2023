package com.alexvar.testOverviewApplication.exception.product;

import com.alexvar.testOverviewApplication.exception.BadRequestException;
import jakarta.annotation.Nonnull;
import java.io.Serial;

/**
 * Exception of {@link BadRequestException} type
 * that corresponds to {@link com.alexvar.testOverviewApplication.persistence.entity.Product} entity.
 *
 * @since 1.0
 * @author AlexVar
 */
public class NotValidProductDataException extends BadRequestException {

  @Serial
  private static final long serialVersionUID = 8798540606586836390L;

  public NotValidProductDataException(@Nonnull final String message) {
    super(message);
  }
}
