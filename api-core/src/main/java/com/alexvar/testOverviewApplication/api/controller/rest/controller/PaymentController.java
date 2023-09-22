package com.alexvar.testOverviewApplication.api.controller.rest.controller;

import com.alexvar.testOverviewApplication.api.mapper.OrderMapper;
import com.alexvar.testOverviewApplication.model.dto.CreditCardCredentialsRequest;
import com.alexvar.testOverviewApplication.model.dto.OrderDto;
import com.alexvar.testOverviewApplication.service.OrderService;
import com.alexvar.testOverviewApplication.service.PaymentService;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for payment {@link com.alexvar.testOverviewApplication.persistence.entity.Order} that was placed in the shop.
 *
 * @see PaymentService
 * @see OrderService
 * @see OrderMapper
 * @since 1.0
 * @author AlexVar
 */
@RestController
@RequiredArgsConstructor
@Validated
public class PaymentController {

  private final PaymentService paymentService;
  private final OrderService orderService;
  private final OrderMapper orderMapper;

  /**
   * Payment operation on desired {@link com.alexvar.testOverviewApplication.persistence.entity.Order}.
   * <p>
   *   By specifying credit card credentials, will execute payment operation on
   *   selected {@link com.alexvar.testOverviewApplication.persistence.entity.Order}, where was specified
   *   desired {@link com.alexvar.testOverviewApplication.persistence.entity.Product} and it quantity.
   * </p>
   *
   * @param orderName selected {@link com.alexvar.testOverviewApplication.persistence.entity.Order} to payment.
   * @param creditCardCredentialsRequest credit card credentials to payment.
   * @return response body of paid {@link OrderDto}.
   */
  @PostMapping("/order/pay")
  public ResponseEntity<OrderDto> payOrder(@RequestParam("orderName") @NotEmpty final String orderName,
                                        @RequestBody @NotNull final CreditCardCredentialsRequest creditCardCredentialsRequest) {
    final var orderToPay = orderService.find(orderName);
    final var paidOrder = paymentService.pay(orderToPay, creditCardCredentialsRequest);
    final var responsePaidOrder = orderMapper.toOrderDto(paidOrder);
    return ResponseEntity.ok(responsePaidOrder);
  }
}
