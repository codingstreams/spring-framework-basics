package com.example.spring_framework_basics.logger;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class PaymentAuditLogger {
  private final LocalDateTime startTime = LocalDateTime.now();
  private final List<String> steps = new ArrayList<>();

  @PostConstruct
  public void init() {
    this.logStep("\n------------- PAYMENT PROCESS STARTED -------------\n");
    this.logStep("Transaction started at: " + startTime);
  }

  public void logStep(String step) {
    steps.add(step + " at " + LocalDateTime.now());
  }

  public void printSummary() {
    steps.forEach(System.out::println);
    System.out.println("\n------------- PAYMENT PROCESS END -------------\n");
  }
}