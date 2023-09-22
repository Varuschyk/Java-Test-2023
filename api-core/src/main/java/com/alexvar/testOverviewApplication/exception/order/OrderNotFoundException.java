package com.alexvar.testOverviewApplication.exception.order;

import com.alexvar.testOverviewApplication.exception.NotFoundException;
import jakarta.annotation.Nonnull;
import java.io.Serial;


/**
 * Exception of {@link NotFoundException} type
 * that corresponds to {@link com.alexvar.testOverviewApplication.persistence.entity.Order} entity.
 *
 * @since 1.0
 * @author AlexVar
 */
public class OrderNotFoundException extends NotFoundException {

  @Serial
  private static final long serialVersionUID = -2716554730769234264L;

  public OrderNotFoundException(@Nonnull final String message) {
    super(message);
  }
}
