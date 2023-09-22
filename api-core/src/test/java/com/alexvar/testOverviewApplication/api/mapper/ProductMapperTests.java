package com.alexvar.testOverviewApplication.api.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.alexvar.testOverviewApplication.persistence.entity.Product;
import com.alexvar.testOverviewApplication.test.contants.mapper.TestProductMapperConstants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ProductMapperTests {

  private final ProductMapper productMapper = new ProductMapperImpl();

  @Test
  void testProductToProductDto() {
    final var productDto = productMapper.toProductDto(TestProductMapperConstants.PRODUCT_ENTITY);

    assertEquals(TestProductMapperConstants.PRODUCT_TITLE, productDto.getTitle());
    assertEquals(TestProductMapperConstants.PRODUCT_SELLER, productDto.getSeller());
    assertEquals(TestProductMapperConstants.PRODUCT_PRICE, productDto.getPrice());
    assertEquals(TestProductMapperConstants.PRODUCT_DESCRIPTION, productDto.getDescription());
    assertEquals(TestProductMapperConstants.PRODUCT_CATEGORIES, productDto.getCategories());
    assertEquals(TestProductMapperConstants.PRODUCT_QUANTITY, productDto.getQuantity());
  }

  @Test
  void testProductToProductDto_WithNullProduct() {
    final var productDto = productMapper.toProductDto(null);
    assertNull(productDto);
  }

  @Test
  void testProductDtoToProduct() {
    final var product = productMapper.toProduct(TestProductMapperConstants.PRODUCT_DTO);

    assertEquals(TestProductMapperConstants.PRODUCT_TITLE, product.getTitle());
    assertEquals(TestProductMapperConstants.PRODUCT_SELLER, product.getSeller());
    assertEquals(TestProductMapperConstants.PRODUCT_PRICE, product.getPrice());
    assertEquals(TestProductMapperConstants.PRODUCT_DESCRIPTION, product.getDescription());
    assertEquals(TestProductMapperConstants.PRODUCT_CATEGORIES, product.getCategories());
    assertEquals(TestProductMapperConstants.PRODUCT_QUANTITY, product.getQuantity());
  }

  @Test
  void testProductDtoToProduct_WithNullProductDto() {
    final var product = productMapper.toProduct(null);
    assertNull(product);
  }

  @Test
  void updateProductValidInputProductIsUpdated() {
    final var product = new Product();

    productMapper.updateProduct(product, TestProductMapperConstants.PRODUCT_DTO);

    assertEquals(TestProductMapperConstants.PRODUCT_TITLE, product.getTitle());
    assertEquals(TestProductMapperConstants.PRODUCT_SELLER, product.getSeller());
    assertEquals(TestProductMapperConstants.PRODUCT_PRICE, product.getPrice());
    assertEquals(TestProductMapperConstants.PRODUCT_DESCRIPTION, product.getDescription());
    assertEquals(TestProductMapperConstants.PRODUCT_CATEGORIES, product.getCategories());
    assertEquals(TestProductMapperConstants.PRODUCT_QUANTITY, product.getQuantity());
  }

  @Test
  void shouldConvertProductListToProductDtoList() {
    final var productDtoList = productMapper.toProductDtoList(TestProductMapperConstants.PRODUCT_ENTITY_LIST);

    assertEquals(TestProductMapperConstants.PRODUCT_ENTITY_LIST.get(0).getTitle(), productDtoList.get(0).getTitle());
    assertEquals(TestProductMapperConstants.PRODUCT_ENTITY_LIST.get(0).getPrice(), productDtoList.get(0).getPrice());
    assertEquals(TestProductMapperConstants.PRODUCT_ENTITY_LIST.get(0).getQuantity(), productDtoList.get(0).getQuantity());
    assertEquals(TestProductMapperConstants.PRODUCT_ENTITY_LIST.get(0).getDescription(), productDtoList.get(0).getDescription());
    assertEquals(TestProductMapperConstants.PRODUCT_ENTITY_LIST.get(0).getSeller(), productDtoList.get(0).getSeller());
    assertEquals(TestProductMapperConstants.PRODUCT_ENTITY_LIST.get(0).getCategories(), productDtoList.get(0).getCategories());
  }
}