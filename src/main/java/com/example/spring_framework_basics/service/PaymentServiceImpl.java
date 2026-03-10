package com.example.spring_framework_basics.service;

import com.example.ext_payment_provider.CurrencyConverter;
import com.example.spring_framework_basics.logger.PaymentAuditLogger;
import com.example.spring_framework_basics.model.Payment;
import com.example.spring_framework_basics.provider.PaymentProvider;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class PaymentServiceImpl implements PaymentService {
  private final PaymentProvider paymentProvider;
  private final CurrencyConverter currencyConverter;
  private final ObjectProvider<PaymentAuditLogger> objectProvider;

  public PaymentServiceImpl(
      @Qualifier("defaultPaymentProvider") PaymentProvider paymentProvider,
      CurrencyConverter converter,
      ObjectProvider<PaymentAuditLogger> objectProvider) {
    this.paymentProvider = paymentProvider;
    this.currencyConverter = converter;
    this.objectProvider = objectProvider;
  }

  @PostConstruct
  public void init() {
    if (Objects.nonNull(paymentProvider)) {
      System.out.println("INIT: Connection Established with the Payment Gateway.");
    }
  }

  @PreDestroy
  public void destroy() {
//    paymentProvider = null;
    System.out.println("DESTROY: Disconnected from the Payment Gateway.");
  }


  @Override
  public Boolean executePayment(@NonNull Payment payment) {
    var paymentAuditLogger = objectProvider.getObject();

    paymentAuditLogger.logStep("Starting payment for: " + payment.amount());
    if (payment.amount() <= 0) {
      paymentAuditLogger.logStep("Service: Payment rejected - Invalid amount.");
      return false;
    }

    paymentAuditLogger.logStep("Service: Routing payment to provider...");

    try {
      // INR --> USD
      if (payment.currency().equalsIgnoreCase("INR")) {
        var exchangedAmount = currencyConverter.convert(payment.amount(), "INR", "USD");
        paymentProvider.process(new Payment(payment.transactionId(), exchangedAmount, "USD"));
      }

      paymentAuditLogger.logStep("PAYMENT COMPLETED");
      paymentAuditLogger.printSummary();

      return true;
    } catch (Exception e) {
      paymentAuditLogger.logStep("Service: Provider failed - " + e.getMessage());
      return false;
    }
  }
}
