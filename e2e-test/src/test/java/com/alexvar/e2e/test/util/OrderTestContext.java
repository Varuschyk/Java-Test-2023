package com.alexvar.e2e.test.util;

import com.alexvar.testOverviewApplication.model.dto.OrderDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Nonnull;
import java.io.File;
import java.util.List;

public class OrderTestContext {

  private final static List<File> fileHolderManager = FileReader
      .readFiles("src/test/resources/e2e-json-entities/e2e-test-scenarios/response-bodies/order");

  @Nonnull
  public static List<File> getJsonFiles() {
    return fileHolderManager.stream()
        .filter(file -> file.getName().endsWith(".json")).toList();
  }

  public static OrderDto provideOrderDtoByName(@Nonnull final String name) {
    final var jsonDeserializer = new JsonDeserializer(new ObjectMapper());
    final var listOrdersDto = jsonDeserializer.read(getJsonFiles(), OrderDto.class);
    return listOrdersDto.stream()
        .filter(orderDto -> orderDto.getName().equals(name)).findFirst().get();
  }
}