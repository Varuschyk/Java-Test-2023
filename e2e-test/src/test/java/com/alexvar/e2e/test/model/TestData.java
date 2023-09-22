package com.alexvar.e2e.test.model;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class TestData {
  @Nonnull
  private final String uri;
  @Nonnull
  private final HttpMethod httpMethod;
  @Nonnull
  private final HttpEntity<?> httpEntity;
  @Nonnull
  private final Class<?> responseType;
  @Nonnull
  private final HttpStatus expectedResponseStatus;
  private final boolean checkBody;
  @Nullable
  private final Object expectedResult;
}
