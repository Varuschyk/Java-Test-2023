package com.alexvar.testOverviewApplication.service;

import com.alexvar.testOverviewApplication.model.dto.CreditCardCredentialsRequest;
import com.alexvar.testOverviewApplication.persistence.entity.Order;
import jakarta.annotation.Nonnull;

/**
 * Service to payments customers {@link Order}.
 *
 * @since 1.0
 * @author AlexVar
 */
public interface PaymentService {

  /**
   * Responsible for payment of customers {@link Order}.
   * <p>
   *   Makes payment operations with specifying {@link CreditCardCredentialsRequest}
   *   with credentials to pay for the goods.
   * </p>
   *
   * @param orderToPay desired {@link Order} to pay.
   * @param creditCardCredentialsRequest credentials of customer's credit card.
   * @return paid {@link Order}.
   */
  Order pay(@Nonnull Order orderToPay,
            @Nonnull CreditCardCredentialsRequest creditCardCredentialsRequest);
}
