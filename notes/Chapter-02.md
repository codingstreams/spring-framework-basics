# CHAPTER 02 - How Spring Framework Finds, Create and Manage Java Class Objects?

- Now, we know that Spring Framework automates the process of creating and managing the Java class objects. But, how it
  is going to find out these classes, creates object and manage their lifecycle.
  ![Working of Spring Framework](/notes/resources/diagrams/chapter-02-01.png)
- **Spring Bean:** A Java class objet managed by Spring Framework.

## Component Scanning

- It needs some Anchor Point (starting point) from where to scan the Java classes (Project Classes). This is known as
  **Component Scanning**. It is not
  recommended to scan the whole codebase because including external libraries makes the process too resource-heavy.
  ![Component Scanning Example](/notes/resources/diagrams/chapter-02-02.png)
- This anchor point is `@SpringBootApplication`.

![Spring Boot Application Annotation](/notes/resources/diagrams/chapter-02-03.png)

```java
package com.example.spring_framework_basics;

@SpringBootApplication
public class Application {

  static void main(String[] args) {
    var applicationContext = SpringApplication.run(Application.class, args);
    var paymentService = applicationContext.getBean(PaymentService.class);
    var payment = new Payment("TX_01", 5466.78, "INR");

    paymentService.executePayment(payment);
  }

}
```

- Here package `com.example.spring_framework_basics`, which contains the anchor point is important. This will become the
  base package and scan all the packages inside this (model, provider and service).

```txt
└── com
    └── example
        └── spring_framework_basics
            ├── Application.java --> Contains @SpringBootApplication --> Anchor Point
            ├── model
            │   └── Payment.java
            ├── provider
            │    ├── BitsPaymentProvider.java
            │    ├── MockPaymentProvider.java
            │    └── PaymentProvider.java
            └── service
                ├── PaymentServiceImpl.java
                └── PaymentService.java
        └── spring_rest_apis --> This will not be scanned.
```

## Bean Identification

- Now that we figured out the scanning of Java classes, how Spring Framework is going to decide what all classes it need
  to manage.
- `@Component` annotation is used to mark the classes which are to be managed by Spring Framework.

![Bean Identification](/notes/resources/diagrams/chapter-02-04.png)

## Application Context

- Now that Components are scanned and objects are created and managed by Spring Framework, how to access those objects?
- This is where `ApplicationContext` comes into the frame. It is created before even the scanning starts, and objects
  are created. It knows where objects are present i.e., **Spring IoC Container**.
  ![Application Context - IoC Container](/notes/resources/diagrams/chapter-02-05.png)
- We will use the Application Context to get the beans that we want to use in our project. Two methods are present, get
  bean using bean name or class type.

![Application Context - Get Beans](/notes/resources/diagrams/chapter-02-06.png)

```java
var applicationContext = SpringApplication.run(Application.class, args);
var paymentService = applicationContext.getBean(PaymentService.class); // By Class Type
var payment = new Payment("TX_01", 5466.78, "INR");

paymentService.executePayment(payment);
```
