package com.alexvar.testOverviewApplication.test.contants.mapper;

import com.alexvar.testOverviewApplication.model.dto.ProductDto;
import com.alexvar.testOverviewApplication.persistence.entity.Product;
import com.alexvar.testOverviewApplication.persistence.model.Category;
import java.util.List;
import java.util.Set;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TestProductMapperConstants {
  public final String PRODUCT_TITLE = "testProductTitle";
  public final String PRODUCT_SELLER = "testProductSeller";
  public final double PRODUCT_PRICE = 50.0;
  public final String PRODUCT_DESCRIPTION = "testProductDescription";
  public final int PRODUCT_QUANTITY = 1;
  public final Set<Category> PRODUCT_CATEGORIES = Set.of(Category.FOOD, Category.TECH);

  public final Product PRODUCT_ENTITY = Product.builder()
      .title(PRODUCT_TITLE)
      .seller(PRODUCT_SELLER)
      .price(PRODUCT_PRICE)
      .description(PRODUCT_DESCRIPTION)
      .quantity(PRODUCT_QUANTITY)
      .categories(PRODUCT_CATEGORIES)
      .build();

  public final ProductDto PRODUCT_DTO = ProductDto.builder()
      .title(PRODUCT_TITLE)
      .seller(PRODUCT_SELLER)
      .price(PRODUCT_PRICE)
      .description(PRODUCT_DESCRIPTION)
      .quantity(PRODUCT_QUANTITY)
      .categories(PRODUCT_CATEGORIES)
      .build();

  public final List<Product> PRODUCT_ENTITY_LIST = List.of(PRODUCT_ENTITY);
}
