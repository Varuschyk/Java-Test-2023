package com.alexvar.testOverviewApplication.service.impl;

import com.alexvar.testOverviewApplication.exception.order.OrderNotFoundException;
import com.alexvar.testOverviewApplication.model.dto.CreditCardCredentialsRequest;
import com.alexvar.testOverviewApplication.persistence.entity.Order;
import com.alexvar.testOverviewApplication.persistence.repository.OrderRepository;
import com.alexvar.testOverviewApplication.service.PaymentService;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Implementation of {@link PaymentService}.
 *
 * @see OrderRepository
 * @since 1.0
 * @author AlexVar
 */
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

  private final OrderRepository orderRepository;

  @Override
  public Order pay(@Nonnull final Order orderToPay,
                     @Nonnull final CreditCardCredentialsRequest creditCardCredentialsRequest) {
    //TODO: Payment operations
    if (!orderRepository.existsByName(orderToPay.getName())) throw new OrderNotFoundException(
        "Product that you want to pay doesn't exist!");
    orderToPay.setPaid(true);
    return orderRepository.save(orderToPay);
  }
}
