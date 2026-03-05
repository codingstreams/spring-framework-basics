package com.example.spring_framework_basics.service;

import com.example.spring_framework_basics.model.Payment;
import com.example.spring_framework_basics.provider.PaymentProvider;

import java.util.Objects;

public class PaymentServiceImpl implements PaymentService {
  private final PaymentProvider paymentProvider;

  public PaymentServiceImpl(PaymentProvider paymentProvider) {
    this.paymentProvider = paymentProvider;
  }

  @Override
  public Boolean executePayment(Payment payment) {
    if (Objects.isNull(payment) || payment.amount() <= 0) {
      System.out.println("Service: Payment rejected - Invalid amount.");
      return false;
    }

    System.out.println("Service: Routing payment to provider...");

    try {
      paymentProvider.process(payment);
      return true;
    } catch (Exception e) {
      System.err.println("Service: Provider failed - " + e.getMessage());
      return false;
    }
  }
}
