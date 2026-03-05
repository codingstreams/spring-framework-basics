package com.example.spring_framework_basics.model;

import org.jspecify.annotations.NonNull;

public record Payment(String transactionId, double amount, String currency) {

  @Override
  public @NonNull String toString() {
    return String.format("Transaction[%s]: %s %.2f", transactionId, currency, amount);
  }
}