# CHAPTER 01 - The Core Problem: The `new` Keyword & Tight Coupling

- Consider the following project and let's understand that why this `new` keyword is a problematic when we talk about
  *Big Enterprise Level Applications*.

![Project Overview](notes/resources/diagrams/chapter-01-01.png "Project Overview")

```java
void main(){
    var paymentProvider = new MockPaymentProvider();
    var paymentService = new PaymentServiceImpl(paymentProvider);

    var payment = new Payment("TX_01", 5466.78, "INR");

    paymentService.executePayment(payment);
}
```

- Consider the above example, we have simple main(). Creating an object of `PaymentProvider` and then using it for
  creating an object for `PaymentService`. Finally `paymentService` for executing the payment.
- Currently, `paymentService` is having just one dependency i.e., one service and one dependency.
- Now, suppose this dependency list grows (for example more than 100 dependencies) it will become hard to manage these
  dependencies manually.
    - **The Issue:** In the code above, `PaymentServiceImpl` is "tightly coupled" to `MockPaymentProvider`. If you want
      to switch to other payment provider, you have to manually find and change every instance of the new keyword across
      your entire codebase.
    - **The Scaling Problem:** As the application grows, you might encounter **Dependency Hell**. Imagine a service that
      requires 10 different database repositories, each requiring its own database configuration.

![Dependencies List Visualization](/notes/resources/diagrams/chapter-01-02.png)

- We need some tool or utility that can automate this repetitive task of creating objects manually and managing them.
  This is not a productive task just a boring repetitive task which is just wasting lot of time instead this can be used
  for improving the application or focus on business logic.
- This whole repetitive task is automated by Spring Framework.It is going to create and manage Java class object for us.
  And also, going to reduce the boilerplate code.
- Spring Framework depends on Inversion of Control and Dependency Injection.

![Spring Framework Building Blocks](/notes/resources/diagrams/chapter-01-03.png)

- Now, the control of creating Java class object is transferred from us (the developer) to the Spring Framework. This is
  known as **Inversion of Control**.
- When we were the objects manually then we were providing the dependency wherever they were required inside the
  project. Now, that Spring Framework is having the control so it is going to provide those dependencies. This is known
  as **Dependency Injection**.
  ![Spring Framework - IoC and DI](/notes/resources/diagrams/chapter-01-04.png)

## Comparison: Manual vs. Spring Management

| Feature         | Manual (Plain Java)                 | Spring Framework                   |
|-----------------|-------------------------------------|------------------------------------|
| Object Creation | Developer uses new                  | Spring IoC Container               |
| Coupling        | Tight: Hard to swap implementations | Loose: Easy to swap via Interfaces |
| Boilerplate     | High (Lots of setup code)           | Low (Handled by Annotations)       |
| Focus           | Infrastructure + Business Logic     | Pure Business Logic                |

## Quick Summary

- **The Problem:** The new keyword leads to rigid, hard-to-test code and manual dependency management.
- **IoC:** A design philosophy where the container manages the object's life.
- **DI:** The mechanism of delivering those managed objects to the classes that need them.
- **Goal:** To achieve Loose Coupling and higher productivity.