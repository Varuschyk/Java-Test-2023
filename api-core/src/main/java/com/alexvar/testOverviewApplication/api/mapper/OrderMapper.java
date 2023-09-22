package com.alexvar.testOverviewApplication.api.mapper;

import com.alexvar.testOverviewApplication.model.dto.OrderDto;
import com.alexvar.testOverviewApplication.persistence.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 * Mapper for {@link Order} entity.
 * <p>
 *   Allows fast with auto implementation create methods for mapping.
 * </p>
 *
 * @since 1.0
 * @author AlexVar
 */
@Mapper(componentModel = "spring")
public interface OrderMapper {

  /**
   * Converts {@link OrderDto} to {@link Order} entity.
   *
   * @param orderDto dto model that will be converted into {@link Order}.
   * @return converted {@link Order} entity.
   */
  Order toOrder(OrderDto orderDto);

  /**
   * Converts {@link Order} entity to {@link OrderDto} representation.
   *
   * @param order entity that will be converted into {@link OrderDto} representation.
   * @return converted {@link OrderDto} representation.
   */
  OrderDto toOrderDto(Order order);

  /**
   * Sets value fields of provided {@link OrderDto} representation to mapping target.
   * <p>
   *   Provided {@link OrderDto} representation will take field values to the specified
   *   parameter that marked {@link MappingTarget} annotation.
   * </p>
   *
   * @param order target to assign values.
   * @param orderDto source from that will be taken field values.
   */
  void updateOrder(@MappingTarget Order order, OrderDto orderDto);
}
