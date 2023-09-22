package com.alexvar.testOverviewApplication.service.impl;

import com.alexvar.testOverviewApplication.api.mapper.ProductMapper;
import com.alexvar.testOverviewApplication.exception.product.ProductNotFoundException;
import com.alexvar.testOverviewApplication.exception.product.NotValidProductDataException;
import com.alexvar.testOverviewApplication.model.dto.ProductDto;
import com.alexvar.testOverviewApplication.persistence.entity.Product;
import com.alexvar.testOverviewApplication.persistence.model.Category;
import com.alexvar.testOverviewApplication.persistence.repository.ProductRepository;
import com.alexvar.testOverviewApplication.service.ProductService;
import jakarta.annotation.Nonnull;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Implementation of {@link ProductService}.
 *
 * @see ProductRepository
 * @see ProductMapper
 * @since 1.0
 * @author AlexVar
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;
  private final ProductMapper productMapper;

  @Nonnull
  @Override
  public List<Product> provideAllAvailableProducts() {
    final var products = productRepository.findAll();
    if (products == null | products.isEmpty()) throw new ProductNotFoundException("No available products was found!");
    return products;
  }

  @Nonnull
  @Override
  public List<Product> provideAllAvailableProductsByCategories(@Nonnull final Set<Category> categories) {
    final var products = productRepository.findAllByCategoriesIn(categories);
    if (products == null | products.isEmpty()) throw new ProductNotFoundException("No available products by categories was found!");
    return products;
  }

  @Nonnull
  @Override
  public Product provideProductByTitle(@Nonnull final String title) {
    final var product = productRepository.findByTitle(title);
    if (product == null) throw new ProductNotFoundException(
        String.format("Product by title: %s not found!", title));
    return product;
  }

  @Nonnull
  @Override
  public Product addProduct(@Nonnull final ProductDto productDto) {
    if (productDto == null) throw new NotValidProductDataException("Product cannot be null!");
    if (productRepository.existsProductByTitle(productDto.getTitle())) throw new NotValidProductDataException(
        String.format("Product with title %s already exists!", productDto.getTitle()));
    final var createdProduct = productMapper.toProduct(productDto);
    final var savedProduct = productRepository.save(createdProduct);
    log.info("Product {} has been added!", savedProduct.getTitle());
    return savedProduct;
  }

  @Nonnull
  @Override
  public Product updateProduct(@Nonnull final ProductDto productDto) {
    if (productDto == null) throw new NotValidProductDataException("Product cannot be null!");
    final var productToUpdate = productRepository.findByTitle(productDto.getTitle());
    productMapper.updateProduct(productToUpdate, productDto);
    final var updatedProduct = productRepository.save(productToUpdate);
    log.info("Product {} has been updated!", productToUpdate.getTitle());
    return updatedProduct;
  }

  @Nonnull
  @Override
  public Product deleteProductByTitle(@Nonnull final String title) {
    if(!productRepository.existsProductByTitle(title))
      throw new ProductNotFoundException("Product by specified title was not found!");
    return productRepository.deleteProductByTitle(title);
  }
}
