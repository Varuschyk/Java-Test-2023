package com.alexvar.testOverviewApplication.persistence.entity;

import com.alexvar.testOverviewApplication.common.constants.OrderValidationConstants;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import java.time.ZonedDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

/**
 * Entity orders of desired goods to buy.
 * <p>
 *   Model of order for the shop that allows customers makes requests to buy desired goods.
 * </p>
 *
 * @see com.alexvar.testOverviewApplication.model.dto.OrderDto
 * @see Product
 * @since 1.0
 * @author AlexVar
 */
@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  @Size(min = OrderValidationConstants.MIN_NAME_SIZE_CONSTRAINT,
      max = OrderValidationConstants.MAX_NAME_SIZE_CONSTRAINT)
  private String name;

  @Column(name = "buyer")
  @Size(min = OrderValidationConstants.MIN_BUYER_SIZE_CONSTRAINT,
      max = OrderValidationConstants.MAX_BUYER_SIZE_CONSTRAINT)
  private String buyer;

  @CreationTimestamp
  @Column(name = "placed")
  private ZonedDateTime placed;

  @Column(name = "is_paid")
  private boolean paid;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private List<Product> products;
}
