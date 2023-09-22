package com.alexvar.e2e.test.data;

import com.alexvar.e2e.constants.TestOrderConstants;
import com.alexvar.e2e.test.model.TestData;
import com.alexvar.e2e.test.util.OrderTestContext;
import com.alexvar.testOverviewApplication.model.dto.OrderDto;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@Getter
@RequiredArgsConstructor
public enum OrderTestScenarios {

  POST_ORDER_TO_THE_SHOP_POST_METHOD_SCENARIO(
      TestOrderConstants.ORDER_CONTROLLER_PATH,
      HttpMethod.POST,
      OrderTestContext.provideOrderDtoByName("testName_placeNewOrder"),
      OrderDto.class,
      HttpStatus.CREATED,
      false,
      null);

  @Nonnull
  private final String uri;
  @Nonnull
  private final HttpMethod httpMethod;
  @Nullable
  private final Object body;
  @Nonnull
  private final Class<?> responseType;
  @Nonnull
  private final HttpStatus expectedResponseStatus;
  private final boolean checkBody;
  @Nullable
  private final Object expectedResult;

  @Nonnull
  public TestData getTestData() {
    final var httpHeaders = new HttpHeaders();
    httpHeaders.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
    return TestData.builder()
        .uri(uri)
        .httpMethod(httpMethod)
        .httpEntity(body != null ? new HttpEntity<>(body, httpHeaders) : new HttpEntity<>(httpHeaders))
        .responseType(responseType)
        .expectedResponseStatus(expectedResponseStatus)
        .checkBody(checkBody)
        .expectedResult(expectedResult).build();
  }
}
