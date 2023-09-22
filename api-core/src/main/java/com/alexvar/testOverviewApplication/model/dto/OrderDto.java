package com.alexvar.testOverviewApplication.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO representation of {@link com.alexvar.testOverviewApplication.persistence.entity.Order} entity.
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
public class OrderDto {

  @NotBlank
  @JsonProperty(value = "name")
  String name;

  @NotBlank
  @JsonProperty(value = "buyer")
  String buyer;

  @NotNull
  @JsonProperty(value = "placed")
  ZonedDateTime placed;

  @JsonProperty(value = "is_paid")
  boolean paid;

  @NotEmpty
  @JsonProperty(value = "products")
  List<ProductDto> products;

}
