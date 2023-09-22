package com.alexvar.testOverviewApplication.api.controller.rest.controller;

import com.alexvar.testOverviewApplication.api.mapper.ProductMapper;
import com.alexvar.testOverviewApplication.model.dto.ProductDto;
import com.alexvar.testOverviewApplication.persistence.model.Category;
import com.alexvar.testOverviewApplication.service.ProductService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for manipulating with {@link com.alexvar.testOverviewApplication.persistence.entity.Product} entity.
 * <p>
 *   Controller responses for adding products, searching them,
 *   update information or removing from the shop.
 * </p>
 *
 * @see ProductService
 * @see ProductMapper
 * @since 1.0
 * @author AlexVar
 */
@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@Validated
public class ProductController {

  private final ProductService productService;
  private final ProductMapper productMapper;

  /**
   * Provides all available in the shop products.
   *
   * @return {@link List<ProductDto>} all available products in the shop.
   */
  @GetMapping //TODO: Pagination
  public ResponseEntity<List<ProductDto>> getAllAvailableProducts() {
    final var products = productService.provideAllAvailableProducts();
    final var response = productMapper.toProductDtoList(products);
    return ResponseEntity.ok(response);
  }

  /**
   * Provides all available in the shop products by categories.
   *
   * @param categories category that has product by which will be found.
   * @return {@link List<ProductDto>} products that was found by categories in the shop.
   */
  @GetMapping(params = {"categories"})
  public ResponseEntity<List<ProductDto>> getProductsByCategories(@RequestParam(name = "categories")
                                                      @NotEmpty final Set<Category> categories) {
    final var products = productService.provideAllAvailableProductsByCategories(categories);
    final var response = productMapper.toProductDtoList(products);
    return ResponseEntity.ok(response);
  }

  /**
   * Provides {@link com.alexvar.testOverviewApplication.persistence.entity.Product} as {@link ProductDto}
   * representation by his @{@link com.alexvar.testOverviewApplication.persistence.entity.Product#getTitle()},
   * if it exists in database.
   * <p>
   *   Returns desired {@link com.alexvar.testOverviewApplication.persistence.entity.Product} by his
   *   {@link com.alexvar.testOverviewApplication.persistence.entity.Product#getTitle()} parameter,
   *   if already exists in database.
   * </p>
   *
   * @param title parameter by that will be find {@link com.alexvar.testOverviewApplication.persistence.entity.Product}.
   * @return {@link ResponseEntity<ProductDto>} object with response body {@link ProductDto} data.
   */
  @GetMapping(params = {"title"})
  public ResponseEntity<ProductDto> getProductByTitle(@RequestParam(name = "title")
                                                          @NotBlank final String title) {
    final var product = productService.provideProductByTitle(title);
    final var response = productMapper.toProductDto(product);
    return ResponseEntity.ok(response);
  }

  /**
   * Adds {@link com.alexvar.testOverviewApplication.persistence.entity.Product} to shop.
   * <p>
   *   Saves provided in request body {@link ProductDto} in database. But before that, it converts
   *   to the {@link com.alexvar.testOverviewApplication.persistence.entity.Product} entity.
   * </p>
   *
   * @param productDto request body of {@link ProductDto} with data.
   * @return {@link ResponseEntity<ProductDto>} object that was added in shop.
   */
  @PostMapping
  public ResponseEntity<ProductDto> addProductToTheShop(@RequestBody @NotNull final ProductDto productDto) {
    final var addedProduct = productService.addProduct(productDto);
    final var response = productMapper.toProductDto(addedProduct);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  /**
   * Updates {@link com.alexvar.testOverviewApplication.persistence.entity.Product} in shop.
   * <p>
   *   Renews existed {@link com.alexvar.testOverviewApplication.persistence.entity.Product} in database,
   *   replacing old data on new provided in request body {@link ProductDto} representation.
   * </p>
   *
   * @param productDto request body of {@link ProductDto} with new data.
   * @return {@link ResponseEntity<ProductDto>} object that was updated in shop.
   */
  @PutMapping
  public ResponseEntity<ProductDto> updateProduct(@RequestBody @NotNull final ProductDto productDto) {
    final var updatedProduct = productService.updateProduct(productDto);
    final var response = productMapper.toProductDto(updatedProduct);
    return ResponseEntity.ok(response);
  }

  /**
   * Deletes {@link com.alexvar.testOverviewApplication.persistence.entity.Product} from shop.
   * <p>
   *   Removes {@link com.alexvar.testOverviewApplication.persistence.entity.Product} from database,
   *   searching by {@link com.alexvar.testOverviewApplication.persistence.entity.Product#getTitle()} parameter.
   * </p>
   *
   * @param title parameter by that will be find {@link com.alexvar.testOverviewApplication.persistence.entity.Product}.
   * @return {@link ResponseEntity<ProductDto>} object that was deleted from shop.
   */
  @DeleteMapping
  public ResponseEntity<ProductDto> deleteProductByTitle(@RequestParam("title") @NotBlank final String title) {
    final var deletedProduct = productService.deleteProductByTitle(title);
    final var response = productMapper.toProductDto(deletedProduct);
    return ResponseEntity.ok(response);
  }
}
