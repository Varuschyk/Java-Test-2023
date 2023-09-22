package com.alexvar.testOverviewApplication.common.constants;

import com.alexvar.testOverviewApplication.persistence.entity.Product;
import lombok.experimental.UtilityClass;

/**
 * Utility class for containing validation constants of
 * {@link com.alexvar.testOverviewApplication.persistence.entity.Product} entity.
 *
 * @since 1.0
 * @author AlexVar
 */
@UtilityClass
public class ProductValidationConstants {
  /**
   * Constraint constant for specifying min size of {@link Product#getTitle()} field.
   */
  public final int MIN_TITLE_SIZE_CONSTRAINT = 1;
  /**
   * Constraint constant for specifying max size of {@link Product#getTitle()} field.
   */
  public final int MAX_TITLE_SIZE_CONSTRAINT = 50;
  /**
   * Constraint constant for specifying min size of {@link Product#getPrice()} field.
   */
  public final int MIN_PRICE_SIZE_CONSTRAINT = 1;
  /**
   * Constraint constant for specifying max size of {@link Product#getDescription()} field.
   */
  public final int MAX_DESCRIPTION_SIZE_CONSTRAINT = 255;

}
