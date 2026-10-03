# Store Application

This project is a Spring Boot learning/demo application inspired by a tutorial by Code with Mosh. It demonstrates dependency injection, bean selection, payment abstraction, and notification handling in a simple store/order flow.

The app has evolved beyond the basic tutorial example and now includes:
- Stripe as the primary payment service
- notification abstraction and manager pattern
- configuration driven by `application.yaml`
- Spring Boot startup behavior that triggers order processing and a sample notification

## What this demonstrates

This project shows several Spring concepts in a compact application:

- constructor-based dependency injection
- bean qualification with `@Qualifier`
- `@Primary` bean selection
- interface-based design with `PaymentService`
- notification services and delegation through a manager
- YAML-based configuration for external service settings
- startup execution in `StoreApplication`

## Stack

- Language: Java
- Java version: 26
- Framework: Spring Boot 4.1.0
- Build tool: Maven
- Main dependencies:
  - `spring-boot-starter`
  - `spring-boot-starter-web`
  - `spring-boot-starter-test`

## Project structure

```text
.
├── pom.xml
├── mvnw
├── mvnw.cmd
├── .mvn/
├── src/
│   └── main/
│       ├── java/com/redjan/store/
│       │   ├── StoreApplication.java
│       │   ├── HomeController.java
│       │   ├── OrderService.java
│       │   ├── PaymentService.java
│       │   ├── StripePaymentService.java
│       │   ├── PayPalPaymentService.java
│       │   ├── NotificationService.java
│       │   ├── NotificationManager.java
│       │   └── ...
│       └── resources/
│           ├── application.yaml
│           └── static/
```

## Current app behavior

`StoreApplication` boots the Spring context, retrieves the main beans, and triggers app logic at startup:

- `OrderService.placeOrder()`
- `NotificationManager.sendNotification("This is a test.")`

The order flow uses a `PaymentService` abstraction, with Stripe selected as the active primary implementation:

```java
@Service("stripe")
@Primary
public class StripePaymentService implements PaymentService {
    ...
}
```

The order service injects the selected payment provider explicitly:

```java
public OrderService(@Qualifier("stripe") PaymentService paymentService) {
    this.paymentService = paymentService;
}
```

## Payment configuration

The app reads Stripe-related properties from `src/main/resources/application.yaml`:

```yaml
spring:
  application:
    name: store

stripe:
  apiUrl: https://stripe:com
  enabled: true
  timeout: 1000
  supported-currencies: USD,EUR,GBP
```

This demonstrates configuration-driven setup for external payment settings.

## Notification layer

The project also includes an abstraction for notifications:

- `NotificationService` interface
- `NotificationManager` as the delegating service
- startup invocation through the application entry point

This shows how a service layer can be composed and injected in Spring.

## How it fits together

- `StoreApplication` starts the Spring application context.
- `OrderService` manages order processing and depends on a `PaymentService`.
- `StripePaymentService` is the main provider in the current implementation.
- `PayPalPaymentService` still exists as an alternate implementation.
- `HomeController` maps the root `/` route to `index.html`.
- `NotificationManager` sends sample messages using a `NotificationService`.

## Quick start

Requirements:
- JDK 26
- Maven wrapper included in the repo

Run from the project root:

```bash
./mvnw spring-boot:run
```

Optional:

```bash
./mvnw package
java -jar target/store-0.0.1-SNAPSHOT.jar
```

Run tests:

```bash
./mvnw test
```

The app will start on the default Spring Boot port:

- http://localhost:8080/

## Notes

- The app is intentionally simple and instructional.
- Startup-side effects are visible in `StoreApplication`, which is useful for demonstration but may not be ideal for production behavior.
- The project currently uses YAML configuration for Stripe values instead of a `.properties` file.

## Contributing

Feel free to fork the project, create a branch, and submit a PR with improvements or refactors.

## License

This project does not currently include a license file. If you intend to publish or share it publicly, consider adding a license such as MIT or Apache 2.0.
