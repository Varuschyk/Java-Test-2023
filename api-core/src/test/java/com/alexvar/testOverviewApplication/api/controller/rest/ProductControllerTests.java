package com.alexvar.testOverviewApplication.api.controller.rest;

import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.alexvar.testOverviewApplication.api.mapper.ProductMapper;
import com.alexvar.testOverviewApplication.service.ProductService;
import com.alexvar.testOverviewApplication.test.contants.controller.rest.TestProductControllerConstants;
import com.alexvar.testOverviewApplication.test.contants.url.TestUrlConstants;
import com.alexvar.testOverviewApplication.test.contants.url.param.TestUrlParameterConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Description;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class ProductControllerTests {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private ProductService productService;

  @MockBean
  private ProductMapper productMapper;

  @Test
  @Description("GET method 200 OK status - return all products")
  void shouldGetAllAvailableProductsSuccessfully() throws Exception {
    when(productService.provideAllAvailableProducts())
        .thenReturn(TestProductControllerConstants.LIST_PRODUCTS_ENTITY);
    when(productMapper.toProductDtoList(TestProductControllerConstants.LIST_PRODUCTS_ENTITY))
        .thenReturn(TestProductControllerConstants.LIST_PRODUCTS_DTO);

    mockMvc.perform(get(TestUrlConstants.PRODUCT_CONTROLLER_PATH)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

    verify(productService, only()).provideAllAvailableProducts();
    verify(productMapper, only()).toProductDtoList(TestProductControllerConstants.LIST_PRODUCTS_ENTITY);
  }

  @Test
  @Description("GET method 200 OK status - return all products by specified categories")
  void shouldGetAllAvailableProductsBySpecifiedCategoriesSuccessfully() throws Exception {
    when(productService.provideAllAvailableProductsByCategories(
        TestProductControllerConstants.PRODUCT_CATEGORIES))
        .thenReturn(TestProductControllerConstants.LIST_PRODUCTS_ENTITY);
    when(productMapper.toProductDtoList(TestProductControllerConstants.LIST_PRODUCTS_ENTITY))
        .thenReturn(TestProductControllerConstants.LIST_PRODUCTS_DTO);

    mockMvc.perform(get(TestUrlConstants.PRODUCT_CONTROLLER_PATH)
            .param(TestUrlParameterConstants.CATEGORIES_PARAMETER_PRODUCT_CONTROLLER_GET,
                TestProductControllerConstants.PRODUCT_CATEGORIES.toString())
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

    verify(productService, only()).provideAllAvailableProductsByCategories(TestProductControllerConstants.PRODUCT_CATEGORIES);
    verify(productMapper, only()).toProductDtoList(TestProductControllerConstants.LIST_PRODUCTS_ENTITY);
  }

  @Test
  @Description("GET method 200 OK status - return all products by specified title")
  void shouldGetAllAvailableProductsBySpecifiedTitleSuccessfully() throws Exception {
    when(productService.provideProductByTitle(TestProductControllerConstants.PRODUCT_TITLE))
        .thenReturn(TestProductControllerConstants.PRODUCT_ENTITY);
    when(productMapper.toProductDto(TestProductControllerConstants.PRODUCT_ENTITY))
        .thenReturn(TestProductControllerConstants.PRODUCT_DTO);

    mockMvc.perform(get(TestUrlConstants.PRODUCT_CONTROLLER_PATH)
            .param(TestUrlParameterConstants.TITLE_PARAMETER_PRODUCT_CONTROLLER_GET,
                TestProductControllerConstants.PRODUCT_TITLE)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

    verify(productService, only()).provideProductByTitle(TestProductControllerConstants.PRODUCT_TITLE);
    verify(productMapper, only()).toProductDto(TestProductControllerConstants.PRODUCT_ENTITY);
  }

  @Test
  @Description("POST method 201 OK status - create product")
  void shouldCreateProvidedRequestBodyProductSuccessfully() throws Exception {
    final var requestJsonValue =
        objectMapper.writeValueAsString(TestProductControllerConstants.PRODUCT_DTO);
    when(productService.addProduct(TestProductControllerConstants.PRODUCT_DTO))
        .thenReturn(TestProductControllerConstants.PRODUCT_ENTITY);
    when(productMapper.toProductDto(TestProductControllerConstants.PRODUCT_ENTITY))
        .thenReturn(TestProductControllerConstants.PRODUCT_DTO);

    mockMvc.perform(post(TestUrlConstants.PRODUCT_CONTROLLER_PATH)
            .content(requestJsonValue)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated());

    verify(productService, only()).addProduct(TestProductControllerConstants.PRODUCT_DTO);
    verify(productMapper, only()).toProductDto(TestProductControllerConstants.PRODUCT_ENTITY);
  }

  @Test
  @Description("POST method 200 OK status - update product")
  void shouldUpdateRequestedProductSuccessfully() throws Exception {
    final var requestJsonValue =
        objectMapper.writeValueAsString(TestProductControllerConstants.PRODUCT_DTO);
    when(productService.updateProduct(TestProductControllerConstants.PRODUCT_DTO))
        .thenReturn(TestProductControllerConstants.PRODUCT_ENTITY);
    when(productMapper.toProductDto(TestProductControllerConstants.PRODUCT_ENTITY))
        .thenReturn(TestProductControllerConstants.PRODUCT_DTO);

    mockMvc.perform(put(TestUrlConstants.PRODUCT_CONTROLLER_PATH)
            .content(requestJsonValue)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

    verify(productService, only()).updateProduct(TestProductControllerConstants.PRODUCT_DTO);
    verify(productMapper, only()).toProductDto(TestProductControllerConstants.PRODUCT_ENTITY);
  }

  @Test
  @Description("POST method 200 OK status - delete product")
  void shouldDeleteProductSuccessfully() throws Exception {
    when(productService.deleteProductByTitle(TestProductControllerConstants.PRODUCT_TITLE))
        .thenReturn(TestProductControllerConstants.PRODUCT_ENTITY);
    when(productMapper.toProductDto(TestProductControllerConstants.PRODUCT_ENTITY))
        .thenReturn(TestProductControllerConstants.PRODUCT_DTO);

    mockMvc.perform(delete(TestUrlConstants.PRODUCT_CONTROLLER_PATH)
            .param(TestUrlParameterConstants.TITLE_PARAMETER_PRODUCT_CONTROLLER_GET,
                TestProductControllerConstants.PRODUCT_TITLE)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

    verify(productService, only()).deleteProductByTitle(TestProductControllerConstants.PRODUCT_TITLE);
    verify(productMapper, only()).toProductDto(TestProductControllerConstants.PRODUCT_ENTITY);
  }

  //TODO: Add more scenarios
}
