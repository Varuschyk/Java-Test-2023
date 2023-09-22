package com.alexvar.testOverviewApplication.test.contants.mapper;

import com.alexvar.testOverviewApplication.model.dto.OrderDto;
import com.alexvar.testOverviewApplication.model.dto.ProductDto;
import com.alexvar.testOverviewApplication.persistence.entity.Order;
import com.alexvar.testOverviewApplication.persistence.entity.Product;
import com.alexvar.testOverviewApplication.persistence.model.Category;
import java.util.List;
import java.util.Set;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TestOrderMapperConstants {

  public final String NAME_ORDER = "testOrderName";
  public final String BUYER_UNPAID_ORDER = "testBuyer";
  public final boolean BOOLEAN_PAID_ORDER = false;
  public final Long ID_UNPAID_ORDER = 1L;

  public final List<Product> INNER_PRODUCTS_IN_ORDER = List.of(Product.builder()
      .id(null)
      .title("testProductTitle")
      .seller("testSeller")
      .price(50.0)
      .description("testDescription")
      .quantity(1)
      .categories(Set.of(Category.TECH))
      .build());

  public final List<ProductDto> INNER_PRODUCTS_DTO_IN_ORDER = List.of(ProductDto.builder()
      .title("testProductTitle")
      .seller("testSeller")
      .price(50.0)
      .description("testDescription")
      .quantity(1)
      .categories(Set.of(Category.TECH))
      .build());

  public final Order ORDER_ENTITY = Order.builder()
      .id(ID_UNPAID_ORDER)
      .name(NAME_ORDER)
      .buyer(BUYER_UNPAID_ORDER)
      .paid(BOOLEAN_PAID_ORDER)
      .products(INNER_PRODUCTS_IN_ORDER)
      .build();

  public final OrderDto ORDER_DTO = OrderDto.builder()
      .name(NAME_ORDER)
      .buyer(BUYER_UNPAID_ORDER)
      .paid(BOOLEAN_PAID_ORDER)
      .products(INNER_PRODUCTS_DTO_IN_ORDER)
      .build();


}
