package com.alexvar.testOverviewApplication.common.constants;

import com.alexvar.testOverviewApplication.persistence.entity.Order;
import lombok.experimental.UtilityClass;

/**
 * Utility class for containing validation constants of
 * {@link com.alexvar.testOverviewApplication.persistence.entity.Order} entity.
 *
 * @since 1.0
 * @author AlexVar
 */
@UtilityClass
public class OrderValidationConstants {

  /**
   * Constraint constant for specifying min size of {@link Order#getName()} field.
   */
  public final int MIN_NAME_SIZE_CONSTRAINT = 1;
  /**
   * Constraint constant for specifying max size of {@link Order#getName()} field.
   */
  public final int MAX_NAME_SIZE_CONSTRAINT = 50;
  /**
   * Constraint constant for specifying min size of {@link Order#getBuyer()} field.
   */
  public final int MIN_BUYER_SIZE_CONSTRAINT = 1;
  /**
   * Constraint constant for specifying max size of {@link Order#getBuyer()} field.
   */
  public final int MAX_BUYER_SIZE_CONSTRAINT = 20;
}
