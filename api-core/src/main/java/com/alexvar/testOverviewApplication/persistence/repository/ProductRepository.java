package com.alexvar.testOverviewApplication.persistence.repository;

import com.alexvar.testOverviewApplication.persistence.entity.Product;
import com.alexvar.testOverviewApplication.persistence.model.Category;
import jakarta.annotation.Nonnull;
import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA repository corresponds to {@link Product} entity.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

  Product findByTitle(@Nonnull String title);

  List<Product> findAllByCategoriesIn(Set<Category> categories);

  Product deleteProductByTitle(@Nonnull String title);

  boolean existsProductByTitle(@Nonnull String title);
}
