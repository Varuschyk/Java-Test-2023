package com.alexvar.testOverviewApplication.service;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.alexvar.testOverviewApplication.api.mapper.ProductMapper;
import com.alexvar.testOverviewApplication.persistence.repository.ProductRepository;
import com.alexvar.testOverviewApplication.service.impl.ProductServiceImpl;
import com.alexvar.testOverviewApplication.test.contants.service.order.TestProductServiceConstants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTests {

  @Mock
  private ProductRepository productRepository;
  @Mock
  private ProductMapper productMapper;
  @InjectMocks
  private ProductServiceImpl productService;

  @Test
  void shouldProvideAllAvailableProductsSuccessfully() {
    when(productRepository.findAllByCategoriesIn(
        TestProductServiceConstants.PRODUCT_CATEGORIES))
        .thenReturn(TestProductServiceConstants.LIST_PRODUCTS_ENTITY);

    productService.provideAllAvailableProductsByCategories(TestProductServiceConstants.PRODUCT_CATEGORIES);

    verify(productRepository, only()).findAllByCategoriesIn(TestProductServiceConstants.PRODUCT_CATEGORIES);
  }

  @Test
  void shouldProvideProductByTitleSuccessfully() {
    when(productRepository.findByTitle(TestProductServiceConstants.PRODUCT_TITLE))
        .thenReturn(TestProductServiceConstants.PRODUCT_ENTITY);

    productService.provideProductByTitle(TestProductServiceConstants.PRODUCT_TITLE);

    verify(productRepository, only()).findByTitle(TestProductServiceConstants.PRODUCT_TITLE);
  }

  @Test
  void shouldAddProductSuccessfully() {
    when(productRepository.existsProductByTitle(TestProductServiceConstants.PRODUCT_TITLE))
        .thenReturn(false);
    when(productMapper.toProduct(TestProductServiceConstants.PRODUCT_DTO))
        .thenReturn(TestProductServiceConstants.PRODUCT_ENTITY);
    when(productRepository.save(TestProductServiceConstants.PRODUCT_ENTITY))
        .thenReturn(TestProductServiceConstants.PRODUCT_ENTITY);

    productService.addProduct(TestProductServiceConstants.PRODUCT_DTO);

    verify(productRepository, times(1)).existsProductByTitle(TestProductServiceConstants.PRODUCT_TITLE);
    verify(productMapper, only()).toProduct(TestProductServiceConstants.PRODUCT_DTO);
    verify(productRepository, times(1)).save(TestProductServiceConstants.PRODUCT_ENTITY);
  }

  @Test
  void shouldUpdateProductSuccessfully() {
    when(productRepository.findByTitle(TestProductServiceConstants.PRODUCT_TITLE))
        .thenReturn(TestProductServiceConstants.PRODUCT_ENTITY);
    doNothing().when(productMapper).updateProduct(TestProductServiceConstants.PRODUCT_ENTITY,
        TestProductServiceConstants.PRODUCT_DTO);
    when(productRepository.save(TestProductServiceConstants.PRODUCT_ENTITY))
        .thenReturn(TestProductServiceConstants.PRODUCT_ENTITY);

    productService.updateProduct(TestProductServiceConstants.PRODUCT_DTO);

    verify(productRepository, times(1)).findByTitle(TestProductServiceConstants.PRODUCT_TITLE);
    verify(productMapper, only()).updateProduct(TestProductServiceConstants.PRODUCT_ENTITY, TestProductServiceConstants.PRODUCT_DTO);
    verify(productRepository, times(1)).save(TestProductServiceConstants.PRODUCT_ENTITY);
  }

  @Test
  void shouldDeleteProductSuccessfully() {
    when(productRepository.existsProductByTitle(TestProductServiceConstants.PRODUCT_TITLE))
        .thenReturn(true);
    when(productRepository.deleteProductByTitle(TestProductServiceConstants.PRODUCT_TITLE))
        .thenReturn(TestProductServiceConstants.PRODUCT_ENTITY);

    productService.deleteProductByTitle(TestProductServiceConstants.PRODUCT_TITLE);

    verify(productRepository, times(1)).existsProductByTitle(TestProductServiceConstants.PRODUCT_TITLE);
    verify(productRepository, times(1)).deleteProductByTitle(TestProductServiceConstants.PRODUCT_TITLE);
  }

  //TODO: Write more scenarios
}