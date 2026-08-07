# store application

This was created by following a demo by Code with Mosh. The demo is titled "Spring Boot Tutorial for Beginners" and can be found on You Tube.  The application demonstrates a simple store/order flow and interchangeable payment service implementations.

## What this demonstrates

A tiny Spring Boot app that shows dependency injection and startup behavior for an order flow. On startup the application obtains the OrderService bean and calls placeOrder() (see `StoreApplication.java`), and a web controller serves the home page.

### Stack
- Language(s): Java (project requires Java 26 as declared in `pom.xml`)
- Framework / runtime: Spring Boot 4.1.0
- Notable libraries:
  - spring-boot-starter
  - spring-boot-starter-web
  - spring-boot-starter-test (test support)

## How it's organized
Top-level important files and directories:

```
pom.xml                    Maven project file (Java 26, Spring Boot parent)
mvnw, mvnw.cmd, .mvn/      Maven wrapper
src/
  main/
    java/
      com/redjan/store/
        StoreApplication.java        Application entry point; runs the Spring context and calls OrderService.placeOrder()
        HomeController.java         Maps GET / to a home page (returns "index.html")
        OrderService.java           Business logic for placing orders
        PaymentService.java         Payment service interface
        StripePaymentService.java   PaymentService implementation (Stripe)
        PayPalPaymentService.java   PaymentService implementation (PayPal)
    resources/
      (static or templates for index.html -- place your UI assets here)
```

How it fits together:
- `StoreApplication` starts the Spring context and explicitly fetches the `OrderService` bean to place an order at startup.
- `OrderService` encapsulates order logic and depends on `PaymentService`. There are two concrete payment implementations (`StripePaymentService`, `PayPalPaymentService`) showing how to swap behavior via Spring configuration/beans.
- `HomeController` serves the application's home page at `/`.

## Quick start — run locally

Requirements:
- JDK 26
- (Optional) Docker if you want containerized runs

From the repository root:

- Run with the included Maven wrapper:
  ```bash
  # Run the app directly
  ./mvnw spring-boot:run

  # Or build and run the jar
  ./mvnw package
  java -jar target/store-0.0.1-SNAPSHOT.jar

  # Run tests
  ./mvnw test
  ```

- Default server port: 8080 (Spring Boot default). Visit:
  - http://localhost:8080/ → returns `index.html` (place the home page under `src/main/resources/static/` or `src/main/resources/templates/` depending on your setup)

## What I checked in the code
- `pom.xml` — project metadata, Java version set to 26, Spring Boot parent 4.1.0, web/test starters.
- `src/main/java/com/redjan/store/StoreApplication.java` — main class that boots Spring and calls `orderService.placeOrder()`.
- `src/main/java/com/redjan/store/HomeController.java` — controller mapping `/` to `index.html`.
- Service classes present: `OrderService.java`, `PaymentService.java`, `StripePaymentService.java`, `PayPalPaymentService.java`.

## Notes & recommendations
- Place the application home page (index.html) under `src/main/resources/static/` for static serving or `src/main/resources/templates/` if you plan to use a template engine.
- Consider wiring the desired `PaymentService` implementation via Spring profiles or configuration so the concrete payment provider can be switched without changing code.
- Because `StoreApplication` calls `orderService.placeOrder()` on startup, be mindful of side effects (external calls) during `spring-boot:run` — you may want to move that invocation to an application runner or enable it under a profile.

## Contributing
- Fork, create a feature branch, open a PR describing the change.
- Add/maintain unit tests for `OrderService` and payment implementations.

## License
Add a LICENSE file or fill in license metadata in `pom.xml` if you intend to open-source this project.

## Try asking
- "Can you add a Dockerfile and Docker Compose to run the app and a mock payment service?"
- "Could you make PaymentService configurable via Spring profiles and add an example profile for Stripe?"
- "Please add unit tests for OrderService and show how to mock PaymentService in tests."
