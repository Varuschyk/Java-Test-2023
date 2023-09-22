package com.alexvar.testOverviewApplication.service.impl;

import static java.util.concurrent.Executors.newSingleThreadScheduledExecutor;

import com.alexvar.testOverviewApplication.api.mapper.OrderMapper;
import com.alexvar.testOverviewApplication.exception.order.NotValidOrderDataException;
import com.alexvar.testOverviewApplication.exception.order.OrderNotFoundException;
import com.alexvar.testOverviewApplication.model.dto.OrderDto;
import com.alexvar.testOverviewApplication.persistence.entity.Order;
import com.alexvar.testOverviewApplication.persistence.holder.OrderHolder;
import com.alexvar.testOverviewApplication.persistence.repository.OrderRepository;
import com.alexvar.testOverviewApplication.service.OrderService;
import jakarta.annotation.Nonnull;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Implementation of {@link OrderService}.
 *
 * @see OrderRepository
 * @see OrderMapper
 * @see OrderHolder
 * @since 1.0
 * @author AlexVar
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

  private final OrderMapper orderMapper;
  private final OrderRepository orderRepository;
  private final OrderHolder orderHolder;

  @Override
  public Order find(@Nonnull final String orderName) {
    final var order = orderRepository.findByName(orderName);
    if (order == null) throw new OrderNotFoundException(
        String.format("Order by name: %s not found!", orderName));
    return order;
  }

  @Override
  public Order place(@Nonnull final OrderDto orderDto) {
    if (orderDto == null) throw new NotValidOrderDataException("Order cannot be null!");
    if (orderRepository.existsByName(orderDto.getName())) throw new NotValidOrderDataException(
        String.format("Order with name %s already exists!", orderDto.getName()));
    final var convertedOrder = orderMapper.toOrder(orderDto);
    final var savedOrder = orderRepository.save(convertedOrder);
    log.info("Order {} has been placed in the shop.", savedOrder.getName());
    deleteUnpaidOrder(savedOrder);
    return savedOrder;
  }

  private void deleteUnpaidOrder(@Nonnull final Order order) {
    final var deleteUnpaidOrderRunnable = new Runnable() {
      @Override
      public void run() {
        if (orderRepository.findById(order.getId()).get().isPaid()) return;
        orderRepository.deleteById(order.getId());
        log.info("Order with id {} was deleted such as was unpaid.", order.getId());
      }
    };

    newSingleThreadScheduledExecutor().schedule(
        deleteUnpaidOrderRunnable,
        orderHolder.getDeleteUnpaidOrdersTimeExecution(),
        TimeUnit.MILLISECONDS);
  }
}
