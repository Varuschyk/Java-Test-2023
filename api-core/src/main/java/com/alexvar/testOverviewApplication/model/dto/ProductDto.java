package com.alexvar.testOverviewApplication.model.dto;

import com.alexvar.testOverviewApplication.persistence.model.Category;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO representation of {@link com.alexvar.testOverviewApplication.persistence.entity.Product} entity.
 * <p>
 *   Uses for request bodies when need to send entity data to the service.
 *   And for response bodies when client wait data the response from the services.
 * </p>
 *
 * @since 1.0
 * @author AlexVar
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDto {

  @NotBlank
  @JsonProperty(value = "title")
  String title;

  @NotBlank
  @JsonProperty(value = "seller")
  String seller;

  @NotNull
  @JsonProperty(value = "price")
  double price;

  @NotNull
  @JsonProperty(value = "quantity")
  int quantity;

  @NotNull
  @JsonProperty(value = "posted")
  ZonedDateTime posted;

  @Nullable
  @JsonProperty(value = "description")
  String description;

  @NotEmpty
  @JsonProperty(value = "categories")
  Set<Category> categories;
}
