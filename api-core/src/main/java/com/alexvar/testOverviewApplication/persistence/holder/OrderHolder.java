package com.alexvar.testOverviewApplication.persistence.holder;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Holder setting properties that used for {@link com.alexvar.testOverviewApplication.persistence.entity.Order} entity.
 */
@Component
@Getter
public class OrderHolder {

  @Value("${order.delete-unpaid-orders-time-execution}")
  private long deleteUnpaidOrdersTimeExecution;
}
