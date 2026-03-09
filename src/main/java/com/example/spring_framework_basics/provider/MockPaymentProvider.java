package com.example.spring_framework_basics.provider;

import com.example.spring_framework_basics.model.Payment;
import org.springframework.stereotype.Component;

@Component("defaultPaymentProvider")
public class MockPaymentProvider implements PaymentProvider {

  @Override
  public void process(Payment payment) {
//    System.out.println("Mock Payment: Authorizing " + payment);
  }
}
