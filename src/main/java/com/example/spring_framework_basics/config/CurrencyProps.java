package com.example.spring_framework_basics.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "currency")
public class CurrencyProps {
  private CurrencyApi api;

  public CurrencyApi api() {
    return api;
  }

  public void setApi(CurrencyApi api) {
    this.api = api;
  }

  public static class CurrencyApi {
    private String key;
    private String url;

    public String key() {
      return key;
    }

    public void setKey(String key) {
      this.key = key;
    }

    public String url() {
      return url;
    }

    public void setUrl(String url) {
      this.url = url;
    }
  }
}
