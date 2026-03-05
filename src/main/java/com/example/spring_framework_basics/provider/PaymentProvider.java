package com.example.spring_framework_basics.provider;

import com.example.spring_framework_basics.model.Payment;

public interface PaymentProvider {

  void process(Payment payment);
}
