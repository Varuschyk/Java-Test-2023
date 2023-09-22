package com.alexvar.testOverviewApplication.exception.product;

import com.alexvar.testOverviewApplication.exception.NotFoundException;
import jakarta.annotation.Nonnull;
import java.io.Serial;

/**
 * Exception of {@link NotFoundException} type
 * that corresponds to {@link com.alexvar.testOverviewApplication.persistence.entity.Product} entity.
 *
 * @since 1.0
 * @author AlexVar
 */
public class ProductNotFoundException extends NotFoundException {

  @Serial
  private static final long serialVersionUID = -1565973484373676412L;

  public ProductNotFoundException(@Nonnull final String message) {
    super(message);
  }
}
