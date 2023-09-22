package com.alexvar.testOverviewApplication.service;

import static org.mockito.Mockito.only;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.alexvar.testOverviewApplication.api.mapper.OrderMapper;
import com.alexvar.testOverviewApplication.persistence.holder.OrderHolder;
import com.alexvar.testOverviewApplication.persistence.repository.OrderRepository;
import com.alexvar.testOverviewApplication.service.impl.OrderServiceImpl;
import com.alexvar.testOverviewApplication.test.contants.service.order.TestOrderServiceConstants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTests {

  @Mock
  private OrderMapper orderMapper;
  @Mock
  private OrderRepository orderRepository;
  @Mock
  private OrderHolder orderHolder;
  @InjectMocks
  private OrderServiceImpl orderService;

  @Test
  void shouldFindOrderByNameSuccessfully() {
    when(orderRepository.findByName(TestOrderServiceConstants.NAME_ORDER))
        .thenReturn(TestOrderServiceConstants.ORDER_ENTITY);

    orderService.find(TestOrderServiceConstants.NAME_ORDER);

    verify(orderRepository, only()).findByName(TestOrderServiceConstants.NAME_ORDER);
  }

  @Test
  void shouldPlaceOrderSuccessfully() {
    when(orderRepository.existsByName(TestOrderServiceConstants.NAME_ORDER)).thenReturn(false);
    when(orderMapper.toOrder(TestOrderServiceConstants.ORDER_DTO))
        .thenReturn(TestOrderServiceConstants.ORDER_ENTITY);
    when(orderRepository.save(TestOrderServiceConstants.ORDER_ENTITY))
        .thenReturn(TestOrderServiceConstants.ORDER_ENTITY);
    when(orderHolder.getDeleteUnpaidOrdersTimeExecution())
        .thenReturn(TestOrderServiceConstants.ORDER_HOLDER_DELETE_UNPAID_ORDERS_MILLISECONDS);

    orderService.place(TestOrderServiceConstants.ORDER_DTO);

    verify(orderRepository, times(1)).existsByName(TestOrderServiceConstants.NAME_ORDER);
    verify(orderMapper, only()).toOrder(TestOrderServiceConstants.ORDER_DTO);
    verify(orderRepository, times(1)).save(TestOrderServiceConstants.ORDER_ENTITY);
    verify(orderHolder, only()).getDeleteUnpaidOrdersTimeExecution();
  }

  //TODO: Write more scenarios

}
