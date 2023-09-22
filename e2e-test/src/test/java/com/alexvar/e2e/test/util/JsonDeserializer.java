package com.alexvar.e2e.test.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Nonnull;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

public class JsonDeserializer {

  private final ObjectMapper objectMapper;

  public JsonDeserializer(@Nonnull final ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  @Nonnull
  public <E> List<E> read(@Nonnull final Collection<File> jsonFiles,
                                 @Nonnull final Class<E> entity) {
    return jsonFiles.stream()
        .map(file -> {
          try {
            return objectMapper.readValue(file, entity);
          } catch (final IOException ioException) {
            throw new RuntimeException(String.format("Exception was occurred on file: %s, on path: %s",
                file.getName(), file.getAbsolutePath()),
                ioException);
          }
        }).toList();
  }
}
