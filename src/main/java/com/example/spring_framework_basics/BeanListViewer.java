package com.example.spring_framework_basics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class BeanListViewer implements CommandLineRunner {
  private final ApplicationContext applicationContext;
  private final Logger log = LoggerFactory.getLogger(BeanListViewer.class);

  public BeanListViewer(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  }

  @Override
  public void run(String... args) throws Exception {
    log.debug("--- Listing all beans provided by Spring Boot ---");
    log.debug(Arrays.toString(applicationContext.getBeanDefinitionNames()));
    log.debug("--- End of bean list ---");
  }
}
