package com.alexvar.testOverviewApplication.test.contants.controller.rest;

import com.alexvar.testOverviewApplication.model.dto.OrderDto;
import com.alexvar.testOverviewApplication.model.dto.ProductDto;
import com.alexvar.testOverviewApplication.persistence.entity.Order;
import com.alexvar.testOverviewApplication.persistence.entity.Product;
import com.alexvar.testOverviewApplication.persistence.model.Category;
import java.util.List;
import java.util.Set;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TestOrderControllerConstants {

  public final String TITLE_INNER_PRODUCT_IN_UNPAID_ORDER = "testProductTitle";
  public final Long ID_INNER_PRODUCT_IN_UNPAID_ORDER = 1L;
  public final String SELLER_INNER_PRODUCT_IN_UNPAID_ORDER = "testSeller";
  public final double PRICE_INNER_PRODUCT_IN_UNPAID_ORDER = 50.0;
  public final String DESCRIPTION_INNER_PRODUCT_IN_UNPAID_ORDER = "testDescription";
  public final int QUANTITY_INNER_PRODUCT_IN_UNPAID_ORDER = 1;
  public final Set<Category> CATEGORIES_INNER_PRODUCT_IN_UNPAID_ORDER = Set.of(Category.TECH);
  public final String NAME_UNPAID_ORDER = "testOrderName";
  public final String BUYER_UNPAID_ORDER = "testBuyer";
  public final Long ID_UNPAID_ORDER = 1L;

  public final Product INNER_PRODUCT_IN_UNPAID_ORDER = Product.builder()
      .id(ID_INNER_PRODUCT_IN_UNPAID_ORDER)
      .title(TITLE_INNER_PRODUCT_IN_UNPAID_ORDER)
      .seller(SELLER_INNER_PRODUCT_IN_UNPAID_ORDER)
      .price(PRICE_INNER_PRODUCT_IN_UNPAID_ORDER)
      .description(DESCRIPTION_INNER_PRODUCT_IN_UNPAID_ORDER)
      .quantity(QUANTITY_INNER_PRODUCT_IN_UNPAID_ORDER)
      .categories(CATEGORIES_INNER_PRODUCT_IN_UNPAID_ORDER)
      .build();

  public final Order UNPAID_ORDER_ENTITY = Order.builder()
      .id(ID_UNPAID_ORDER)
      .name(NAME_UNPAID_ORDER)
      .buyer(BUYER_UNPAID_ORDER)
      .paid(false)
      .products(List.of(INNER_PRODUCT_IN_UNPAID_ORDER))
      .build();

  public final ProductDto INNER_PRODUCT_DTO_IN_UNPAID_ORDER = ProductDto.builder()
      .title(TITLE_INNER_PRODUCT_IN_UNPAID_ORDER)
      .seller(SELLER_INNER_PRODUCT_IN_UNPAID_ORDER)
      .price(PRICE_INNER_PRODUCT_IN_UNPAID_ORDER)
      .description(DESCRIPTION_INNER_PRODUCT_IN_UNPAID_ORDER)
      .quantity(QUANTITY_INNER_PRODUCT_IN_UNPAID_ORDER)
      .categories(CATEGORIES_INNER_PRODUCT_IN_UNPAID_ORDER)
      .build();

  public final OrderDto UNPAID_ORDER_DTO = OrderDto.builder()
      .name(NAME_UNPAID_ORDER)
      .buyer(BUYER_UNPAID_ORDER)
      .paid(false)
      .products(List.of(INNER_PRODUCT_DTO_IN_UNPAID_ORDER))
      .build();
}
