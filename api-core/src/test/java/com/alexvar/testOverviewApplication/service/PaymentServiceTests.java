package com.alexvar.testOverviewApplication.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.alexvar.testOverviewApplication.exception.order.OrderNotFoundException;
import com.alexvar.testOverviewApplication.persistence.repository.OrderRepository;
import com.alexvar.testOverviewApplication.service.impl.PaymentServiceImpl;
import com.alexvar.testOverviewApplication.test.contants.service.order.TestPaymentServiceConstants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTests {

  @Mock
  private OrderRepository orderRepository;
  @InjectMocks
  private PaymentServiceImpl paymentService;

  @Test
  void shouldExecutePayOperationSuccessfully() {
    when(orderRepository.existsByName(TestPaymentServiceConstants.NAME_UNPAID_ORDER))
        .thenReturn(true);
    when(orderRepository.save(TestPaymentServiceConstants.PAID_ORDER_ENTITY))
        .thenReturn(TestPaymentServiceConstants.PAID_ORDER_ENTITY);

    paymentService.pay(TestPaymentServiceConstants.UNPAID_ORDER_ENTITY,
        TestPaymentServiceConstants.CREDIT_CARD_CREDENTIALS_REQUEST);

    verify(orderRepository, times(1)).existsByName(TestPaymentServiceConstants.NAME_UNPAID_ORDER);
    verify(orderRepository, times(1)).save(TestPaymentServiceConstants.PAID_ORDER_ENTITY);
  }

  @Test
  void shouldThrowExceptionWhenUnpaidOrderNotFound() {
    when(orderRepository.existsByName(TestPaymentServiceConstants.NAME_UNPAID_ORDER))
        .thenReturn(false);

    assertThrows(OrderNotFoundException.class, () ->
        paymentService.pay(TestPaymentServiceConstants.UNPAID_ORDER_ENTITY,
        TestPaymentServiceConstants.CREDIT_CARD_CREDENTIALS_REQUEST));

    verify(orderRepository, only()).existsByName(TestPaymentServiceConstants.NAME_UNPAID_ORDER);
  }
}