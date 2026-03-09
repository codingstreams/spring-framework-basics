package com.example.spring_framework_basics;

import com.example.ext_payment_provider.MaxApiUsageLimitReached;
import com.example.spring_framework_basics.model.Payment;
import com.example.spring_framework_basics.service.PaymentServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.spring_framework_basics", "com.example.spring_rest"})
public class Application {
  private static Integer TXN_COUNT = 0;

  static void main(String[] args) {
    var applicationContext = SpringApplication.run(Application.class, args);
    var paymentService = (PaymentServiceImpl) applicationContext.getBean("paymentServiceImpl");

    try {
      for (String arg : args) {
        double amount = Double.parseDouble(arg);

        var payment = new Payment("TX_0%s".formatted(TXN_COUNT++), amount, "INR");
        paymentService.executePayment(payment);
      }
    } catch (NumberFormatException e) {
      System.err.println("Error: Invalid amount format. Please enter a number.");
    } catch (MaxApiUsageLimitReached e) {
      System.err.println("API ERROR: " + e.getMessage());
    } catch (Exception e) {
      System.err.println("SYSTEM ERROR: " + e.getMessage());
    }
  }

}

