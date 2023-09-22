package com.alexvar.e2e.config;

import java.io.IOException;
import java.util.stream.Stream;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

@TestConfiguration
public class TestConfig {

  @Autowired
  private DataSource dataSource;

  @EventListener(ApplicationReadyEvent.class)
  public void insertInitData() throws IOException {
    final var resourceDatabasePopulator = new ResourceDatabasePopulator();
    resourceDatabasePopulator.setContinueOnError(false);
    resourceDatabasePopulator.addScripts(getResources());
    resourceDatabasePopulator.execute(dataSource);
  }

  private Resource[] getResources() throws IOException {
    final var insertOrderScripts = new PathMatchingResourcePatternResolver().getResources(
        "classpath:\\e2e-test\\src\\test\\resources\\db.changelog\\insert_data\\order*.sql");
    return Stream.of(insertOrderScripts).toArray(Resource[]::new);
  }
}
