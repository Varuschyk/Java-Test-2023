package com.alexvar.testOverviewApplication.config;

import jakarta.annotation.Nonnull;
import org.springframework.boot.convert.ApplicationConversionService;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Main application web configuration class.
 * <p>
 *   Consists required settings for Web development.
 * </p>
 *
 * @since 1.0
 * @author AlexVar
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

  /**
   * Allows writes request parameters independently of register.
   * <p>
   *   For example:
   *   'http://localhost:8080/api/shop?search=...'
   *   parameter, pArAmEtEr, Parameter, pARAMETER, PARAMETER, etc.
   * </p>
   *
   * @param registry register {@link WebMvcConfigurer}.
   */
  @Override
  public void addFormatters(@Nonnull final FormatterRegistry registry) {
    ApplicationConversionService.configure(registry);
  }
}
