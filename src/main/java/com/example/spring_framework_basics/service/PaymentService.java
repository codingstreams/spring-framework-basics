package com.example.spring_framework_basics.service;

import com.example.spring_framework_basics.model.Payment;

public interface PaymentService {

    Boolean executePayment(Payment payment);
}
