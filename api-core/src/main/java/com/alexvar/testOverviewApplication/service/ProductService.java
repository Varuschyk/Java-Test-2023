package com.alexvar.testOverviewApplication.service;

import com.alexvar.testOverviewApplication.model.dto.ProductDto;
import com.alexvar.testOverviewApplication.persistence.entity.Product;
import com.alexvar.testOverviewApplication.persistence.model.Category;
import jakarta.annotation.Nonnull;
import java.util.List;
import java.util.Set;

/**
 * Service with CRUD operations corresponds to {@link Product} entity.
 *
 * @since 1.0
 * @author AlexVar
 */
public interface ProductService {

  /**
   * Provides all available in the shop {@link Product}.
   *
   * @return {@link List<Product>} that was found in the shop.
   */
  @Nonnull
  List<Product> provideAllAvailableProducts();

  /**
   * Provides all available in the shop {@link Product} by {@link Category}.
   *
   * @return {@link List<Product>} that was found in the shop by {@link Category}.
   */
  @Nonnull
  List<Product> provideAllAvailableProductsByCategories(@Nonnull Set<Category> categories);

  /**
   * Provides {@link Product} by {@link Product#getTitle()} field.
   *
   * @param title field by that will be found {@link Product}.
   * @return founded by parameter {@link Product}.
   */
  @Nonnull
  Product provideProductByTitle(@Nonnull String title);

  /**
   * Adds {@link Product} to the database providing {@link ProductDto} parameter.
   *
   * @param productDto DTO representation of {@link Product}.
   * @return added to the database {@link Product}.
   */
  @Nonnull
  Product addProduct(@Nonnull ProductDto productDto);

  /**
   * Updates {@link Product} in the database providing new data {@link ProductDto} parameter.
   *
   * @param productDto DTO representation of {@link Product}.
   * @return updated in the database {@link Product}.
   */
  @Nonnull
  Product updateProduct(@Nonnull ProductDto productDto);

  /**
   * Deletes {@link Product} from the database by {@link Product#getTitle()} parameter.
   *
   * @param title field by that will be deleted {@link Product}.
   * @return deleted from the database {@link Product}.
   */
  @Nonnull
  Product deleteProductByTitle(@Nonnull String title);

}
