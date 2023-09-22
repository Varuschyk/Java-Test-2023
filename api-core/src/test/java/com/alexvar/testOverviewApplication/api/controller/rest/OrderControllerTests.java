package com.alexvar.testOverviewApplication.api.controller.rest;

import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.alexvar.testOverviewApplication.api.mapper.OrderMapper;
import com.alexvar.testOverviewApplication.exception.order.NotValidOrderDataException;
import com.alexvar.testOverviewApplication.service.OrderService;
import com.alexvar.testOverviewApplication.test.contants.controller.rest.TestOrderControllerConstants;
import com.alexvar.testOverviewApplication.test.contants.url.TestUrlConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class OrderControllerTests {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private OrderService orderService;

  @MockBean
  private OrderMapper orderMapper;

  @Test
  void shouldPlaceOrderInTheShopSuccessfully() throws Exception {
    final var jsonRequestBody = objectMapper.writeValueAsString(TestOrderControllerConstants.UNPAID_ORDER_DTO);
    when(orderService.place(TestOrderControllerConstants.UNPAID_ORDER_DTO))
        .thenReturn(TestOrderControllerConstants.UNPAID_ORDER_ENTITY);
    when(orderMapper.toOrderDto(TestOrderControllerConstants.UNPAID_ORDER_ENTITY))
        .thenReturn(TestOrderControllerConstants.UNPAID_ORDER_DTO);

    mockMvc.perform(post(TestUrlConstants.ORDER_CONTROLLER_PATH)
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonRequestBody))
        .andExpect(status().isCreated());

    verify(orderService, only()).place(TestOrderControllerConstants.UNPAID_ORDER_DTO);
    verify(orderMapper, only()).toOrderDto(TestOrderControllerConstants.UNPAID_ORDER_ENTITY);
  }

  @Test
  void shouldThrowBadRequestExceptionTypeIfOrderAlreadyExists() throws Exception {
    final var jsonRequestBody = objectMapper.writeValueAsString(TestOrderControllerConstants.UNPAID_ORDER_DTO);
    when(orderService.place(TestOrderControllerConstants.UNPAID_ORDER_DTO))
        .thenThrow(NotValidOrderDataException.class);

    mockMvc.perform(post(TestUrlConstants.ORDER_CONTROLLER_PATH)
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonRequestBody))
        .andExpect(status().isBadRequest());

    verify(orderService, only()).place(TestOrderControllerConstants.UNPAID_ORDER_DTO);
  }

  @Test
  void shouldThrowBadRequestExceptionTypeIfProvidedOrderInRequestBodyNotValid() throws Exception {
    mockMvc.perform(post(TestUrlConstants.ORDER_CONTROLLER_PATH)
            .contentType(MediaType.APPLICATION_JSON)
            .content(""))
        .andExpect(status().isBadRequest());
  }
}
