package com.example.spring_framework_basics;

import com.example.spring_framework_basics.model.Payment;
import com.example.spring_framework_basics.service.PaymentService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

  static void main(String[] args) {
    var applicationContext = SpringApplication.run(Application.class, args);
    var paymentService = applicationContext.getBean(PaymentService.class);
    var payment = new Payment("TX_01", 5466.78, "INR");

    paymentService.executePayment(payment);
  }

}

