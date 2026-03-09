package com.example.spring_framework_basics.config;

import com.example.ext_payment_provider.CurrencyConverter;
import com.example.ext_payment_provider.ExchangeRateProvider;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({CurrencyProps.class})
public class AppConfig {

  private CurrencyProps currencyProps;

  public AppConfig(CurrencyProps currencyProps) {
    this.currencyProps = currencyProps;

    System.out.println("API KEY: " + currencyProps.api().key());
    System.out.println("API URL: " + currencyProps.api().url());
  }

  @Bean
  ExchangeRateProvider rateProvider() {
    return new ExchangeRateProvider(
        currencyProps.api().key(),
        currencyProps.api().url()
    );
  }

  @Bean
  CurrencyConverter currencyConverter(ExchangeRateProvider rateProvider) {
    return new CurrencyConverter(rateProvider);
  }
}
