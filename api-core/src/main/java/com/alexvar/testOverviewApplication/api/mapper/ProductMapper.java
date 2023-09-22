package com.alexvar.testOverviewApplication.api.mapper;

import com.alexvar.testOverviewApplication.model.dto.ProductDto;
import com.alexvar.testOverviewApplication.persistence.entity.Product;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 * Mapper for {@link Product} entity.
 * <p>
 *   Allows fast with auto implementation create methods for mapping.
 * </p>
 *
 * @since 1.0
 * @author AlexVar
 */
@Mapper(componentModel = "spring")
public interface ProductMapper {

  /**
   * Converts {@link ProductDto} to {@link Product} entity.
   *
   * @param productDto dto model that will be converted into {@link Product}.
   * @return converted {@link Product} entity.
   */
  Product toProduct(ProductDto productDto);

  /**
   * Converts {@link Product} entity to {@link ProductDto} representation.
   *
   * @param product entity that will be converted into {@link ProductDto} representation.
   * @return converted {@link ProductDto} representation.
   */
  ProductDto toProductDto(Product product);

  /**
   * Converts list of {@link Product} into list of {@link ProductDto}.
   *
   * @param productList list of {@link Product} that will be converted.
   * @return the resulting list of {@link ProductDto} objects.
   */
  List<ProductDto> toProductDtoList(List<Product> productList);

  /**
   * Sets value fields of provided {@link ProductDto} representation to mapping target.
   * <p>
   *   Provided {@link ProductDto} representation will take field values to the specified
   *   parameter that marked {@link MappingTarget} annotation.
   * </p>
   *
   * @param product target to assign values.
   * @param productDto source from that will be taken field values.
   */
  void updateProduct(@MappingTarget Product product, ProductDto productDto);
}
