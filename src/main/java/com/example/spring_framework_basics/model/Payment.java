package com.example.spring_framework_basics.model;

public class Payment {
    private final String transactionId;
    private final double amount;
    private final String currency;

    public Payment(String transactionId, double amount, String currency) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.currency = currency;
    }

    public String currency() {
        return currency;
    }

    public double amount() {
        return amount;
    }

    public String transactionId() {
        return transactionId;
    }

    @Override
    public String toString() {
        return String.format("Transaction[%s]: %s %.2f", transactionId, currency, amount);
    }
}