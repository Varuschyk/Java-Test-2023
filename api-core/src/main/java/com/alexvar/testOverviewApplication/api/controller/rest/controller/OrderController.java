package com.alexvar.testOverviewApplication.api.controller.rest.controller;

import com.alexvar.testOverviewApplication.api.mapper.OrderMapper;
import com.alexvar.testOverviewApplication.model.dto.OrderDto;
import com.alexvar.testOverviewApplication.service.OrderService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for placing customers orders to buy available goods in the shop.
 *
 * @see OrderService
 * @see OrderMapper
 * @since 1.0
 * @author AlexVar
 */
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@Validated
public class OrderController {

  private final OrderService orderService;
  private final OrderMapper orderMapper;

  /**
   * Places order in the shop to buy desired {@link com.alexvar.testOverviewApplication.persistence.entity.Product}.
   * <p>
   *   Allowed to specify multiple {@link com.alexvar.testOverviewApplication.persistence.entity.Product}
   *   in one {@link com.alexvar.testOverviewApplication.persistence.entity.Order}.
   * </p>
   *
   * @param orderDto request body order with desired {@link com.alexvar.testOverviewApplication.persistence.entity.Product}.
   * @return placed in the shop {@link OrderDto}.
   */
  @PostMapping
  public ResponseEntity<OrderDto> placeOrderInTheShop(@RequestBody @NotNull final OrderDto orderDto) {
    final var addedProduct = orderService.place(orderDto);
    final var response = orderMapper.toOrderDto(addedProduct);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

}
