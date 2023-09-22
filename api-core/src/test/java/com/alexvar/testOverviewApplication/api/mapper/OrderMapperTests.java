package com.alexvar.testOverviewApplication.api.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.alexvar.testOverviewApplication.persistence.entity.Order;
import com.alexvar.testOverviewApplication.test.contants.mapper.TestOrderMapperConstants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class OrderMapperTests {

  private final OrderMapper orderMapper = new OrderMapperImpl();

  @Test
  void testOrderToOrderDto() {
    final var orderDto = orderMapper.toOrderDto(TestOrderMapperConstants.ORDER_ENTITY);

    assertEquals(TestOrderMapperConstants.NAME_ORDER, orderDto.getName());
    assertEquals(TestOrderMapperConstants.BUYER_UNPAID_ORDER, orderDto.getBuyer());
    assertEquals(TestOrderMapperConstants.INNER_PRODUCTS_DTO_IN_ORDER, orderDto.getProducts());
  }

  @Test
  void testOrderToOrderDto_WithNullOrder() {
    final var orderDto = orderMapper.toOrderDto(null);
    assertNull(orderDto);
  }

  @Test
  void testOrderDtoToOrder() {
    final var order = orderMapper.toOrder(TestOrderMapperConstants.ORDER_DTO);

    assertEquals(TestOrderMapperConstants.NAME_ORDER, order.getName());
    assertEquals(TestOrderMapperConstants.BUYER_UNPAID_ORDER, order.getBuyer());
    assertEquals(TestOrderMapperConstants.INNER_PRODUCTS_IN_ORDER, order.getProducts());
  }

  @Test
  void testOrderDtoToOrder_WithNullOrderDto() {
    final var order = orderMapper.toOrder(null);
    assertNull(order);
  }

  @Test
  void updateOrderValidInputOrderIsUpdated() {
    final var order = new Order();

    orderMapper.updateOrder(order, TestOrderMapperConstants.ORDER_DTO);

    assertEquals(TestOrderMapperConstants.NAME_ORDER, order.getName());
    assertEquals(TestOrderMapperConstants.BUYER_UNPAID_ORDER, order.getBuyer());
    assertEquals(TestOrderMapperConstants.INNER_PRODUCTS_IN_ORDER, order.getProducts());
  }
}