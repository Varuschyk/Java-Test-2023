package com.alexvar.testOverviewApplication.api.controller.rest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.alexvar.testOverviewApplication.api.mapper.OrderMapper;
import com.alexvar.testOverviewApplication.exception.order.NotValidOrderDataException;
import com.alexvar.testOverviewApplication.exception.order.OrderNotFoundException;
import com.alexvar.testOverviewApplication.service.OrderService;
import com.alexvar.testOverviewApplication.service.PaymentService;
import com.alexvar.testOverviewApplication.test.contants.controller.rest.TestPaymentControllerConstants;
import com.alexvar.testOverviewApplication.test.contants.url.TestUrlConstants;
import com.alexvar.testOverviewApplication.test.contants.url.param.TestUrlParameterConstants;
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
public class PaymentControllerTests {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private PaymentService paymentService;

  @MockBean
  private OrderService orderService;

  @MockBean
  private OrderMapper orderMapper;

  @Test
  void shouldPayOrderSuccessfully() throws Exception {
    final var jsonRequestCreditCardBody = objectMapper.writeValueAsString(
        TestPaymentControllerConstants.CREDIT_CARD_CREDENTIALS_REQUEST);

    when(orderService.find(TestPaymentControllerConstants.NAME_UNPAID_ORDER))
        .thenReturn(TestPaymentControllerConstants.UNPAID_ORDER_ENTITY);
    when(paymentService.pay(eq(TestPaymentControllerConstants.UNPAID_ORDER_ENTITY), any()))
        .thenReturn(TestPaymentControllerConstants.PAID_ORDER_ENTITY);
    when(orderMapper.toOrderDto(TestPaymentControllerConstants.PAID_ORDER_ENTITY))
        .thenReturn(TestPaymentControllerConstants.PAID_ORDER_DTO);

    mockMvc.perform(post(TestUrlConstants.PAYMENT_CONTROLLER_PATH)
            .param(TestUrlParameterConstants.ORDER_NAME_PARAMETER_PAYMENT_CONTROLLER_POST,
                TestPaymentControllerConstants.NAME_UNPAID_ORDER)
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonRequestCreditCardBody))
        .andExpect(status().isOk());

    verify(orderService, only()).find(TestPaymentControllerConstants.NAME_UNPAID_ORDER);
    verify(paymentService, only()).pay(eq(TestPaymentControllerConstants.UNPAID_ORDER_ENTITY), any());
    verify(orderMapper, only()).toOrderDto(TestPaymentControllerConstants.PAID_ORDER_ENTITY);
  }

  @Test
  void shouldThrowNotFoundExceptionTypeWhenOrderToPayNotFound() throws Exception {
    final var jsonRequestCreditCardBody = objectMapper.writeValueAsString(
        TestPaymentControllerConstants.CREDIT_CARD_CREDENTIALS_REQUEST);

    when(orderService.find(TestPaymentControllerConstants.NAME_UNPAID_ORDER))
        .thenThrow(OrderNotFoundException.class);

    mockMvc.perform(post(TestUrlConstants.PAYMENT_CONTROLLER_PATH)
            .param(TestUrlParameterConstants.ORDER_NAME_PARAMETER_PAYMENT_CONTROLLER_POST,
                TestPaymentControllerConstants.NAME_UNPAID_ORDER)
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonRequestCreditCardBody))
        .andExpect(status().isNotFound());

    verify(orderService, only()).find(TestPaymentControllerConstants.NAME_UNPAID_ORDER);
  }

  @Test
  void shouldThrowBadRequestExceptionTypeWhenOrderToPayNotValid() throws Exception {
    final var jsonRequestCreditCardBody = objectMapper.writeValueAsString(
        TestPaymentControllerConstants.CREDIT_CARD_CREDENTIALS_REQUEST);

    when(orderService.find(""))
        .thenThrow(NotValidOrderDataException.class);

    mockMvc.perform(post(TestUrlConstants.PAYMENT_CONTROLLER_PATH)
            .param(TestUrlParameterConstants.ORDER_NAME_PARAMETER_PAYMENT_CONTROLLER_POST, "")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonRequestCreditCardBody))
        .andExpect(status().isBadRequest());

    verify(orderService, only()).find("");
  }
}
