package com.alexvar.testOverviewApplication.persistence.repository;

import com.alexvar.testOverviewApplication.persistence.entity.Order;
import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA repository corresponds to {@link Order} entity.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

  Order findByName(@Nonnull String orderName);

  boolean existsByName(@Nonnull String orderName);
}
