package com.alexvar.testOverviewApplication.persistence.entity;

import com.alexvar.testOverviewApplication.common.constants.ProductValidationConstants;
import com.alexvar.testOverviewApplication.persistence.model.Category;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import java.time.ZonedDateTime;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

/**
 * Entity goods that will be containing in database.
 * <p>
 *   Model of product for the shop with all required fields
 *   to providing information about product to customer.
 * </p>
 *
 * @see com.alexvar.testOverviewApplication.model.dto.ProductDto
 * @see Category
 * @since 1.0
 * @author AlexVar
 */
@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Size(min = ProductValidationConstants.MIN_TITLE_SIZE_CONSTRAINT,
      max = ProductValidationConstants.MAX_TITLE_SIZE_CONSTRAINT)
  @Column(name = "title")
  private String title;

  @Column(name = "seller")
  private String seller;

  @Size(min = ProductValidationConstants.MIN_PRICE_SIZE_CONSTRAINT)
  @Column(name = "price")
  private double price;

  @Size
  @Column(name = "quantity")
  private int quantity;

  @CreationTimestamp
  @Column(name = "posted")
  private ZonedDateTime posted;

  @Size(max = ProductValidationConstants.MAX_DESCRIPTION_SIZE_CONSTRAINT)
  @Column(name = "description")
  private String description;

  @ElementCollection(targetClass = Category.class, fetch = FetchType.EAGER)
  @Enumerated(EnumType.STRING)
  @Column(name = "categories")
  private Set<Category> categories;

}
