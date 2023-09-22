package com.alexvar.testOverviewApplication.service;

import com.alexvar.testOverviewApplication.model.dto.OrderDto;
import com.alexvar.testOverviewApplication.persistence.entity.Order;
import jakarta.annotation.Nonnull;

/**
 * Service to placement customers {@link Order} with desired goods to buy in the shop.
 *
 * @since 1.0
 * @author AlexVar
 */
public interface OrderService {

  /**
   * Searches {@link Order} by his {@link Order#getName()}.
   *
   * @param orderName name of {@link Order}.
   * @return found {@link Order} by his {@link Order#getName()}.
   */
  Order find(@Nonnull String orderName);

  /**
   * Places desired customers {@link Order} in the shop.
   *
   * @param orderDto requested desired order to the shop.
   * @return desired customers {@link Order}.
   */
  Order place(@Nonnull OrderDto orderDto);
}
