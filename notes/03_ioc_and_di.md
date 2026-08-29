# Dependency Injection & Inversion of Control (IoC)

This note explains how Spring achieves **Loose Coupling** through **Inversion of Control (IoC)** and **Dependency Injection (DI)**.

---

## 1. Tight Coupling vs. Loose Coupling

### Tight Coupling
Tight coupling occurs when a class depends directly on concrete implementations of other classes. If you need to swap the dependency, you must modify the class code.

```java
// Example of Tight Coupling
public class NotificationController {
    // Direct dependency on a concrete class
    private DummyNotificationServiceImpl notificationService = new DummyNotificationServiceImpl();
}
```
*Problem*: If we want to switch to `ProdNotificationService`, we have to change the controller's code, compile it, and redeploy it.

### Loose Coupling
Loose coupling is achieved by coding to **interfaces** rather than concrete implementations and letting an external manager provide the implementation.

```java
// Example of Loose Coupling
public class NotificationController {
    private final NotificationService notificationService;

    // Dependency is passed via constructor
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }
}
```
In our project:
- [NotificationController.java](file:///Users/anurag/Desktop/crudSpingBootDemo/src/main/java/in/anurag/crudSpingBootDemo/controller/NotificationController.java) doesn't know *which* concrete notification service is used.
- It only knows the [NotificationService](file:///Users/anurag/Desktop/crudSpingBootDemo/src/main/java/in/anurag/crudSpingBootDemo/service/NotificationService.java) interface.
- The actual instance is injected at runtime.

---

## 2. Inversion of Control (IoC) & Dependency Injection (DI)

- **Inversion of Control (IoC)**: A design principle where the control of object creation, configuration, and lifecycle is inverted (transferred) from the developer/application code to a container or framework.
- **Dependency Injection (DI)**: The design pattern used to implement IoC. Instead of objects creating their dependencies, they declare what they need, and the IoC container "injects" those dependencies at runtime.

### Spring IoC Container
The Spring IoC container is responsible for:
1. Instantiating beans (objects).
2. Configuring beans.
3. Assembling dependencies between beans.
4. Managing the bean lifecycle.

Spring provides two types of IoC container implementations:
- `BeanFactory`: Basic container supporting lazy initialization.
- `ApplicationContext`: Advanced container extending `BeanFactory` with enterprise features (e.g., internationalization, event publishing, AOP, web support). Spring Boot uses `ApplicationContext`.

---

## 3. Registering and Injecting Beans

A **Bean** is simply an object managed by the Spring IoC container. We register and inject them using annotations:

### Creating Beans

1. **Class-level (Component Scanning)**: Use stereotype annotations.
   - **`@Component`**: The generic stereotype annotation.
   - **`@Service`**: Specialization of `@Component` for service/business logic classes.
   - **`@Repository`**: Specialization of `@Component` for database access classes.
   - **`@Controller` / `@RestController`**: Specialization of `@Component` for web layer mapping.
2. **Method-level (`@Bean`)**: Used inside class marked `@Configuration`. Useful for registering beans of third-party libraries where you cannot edit the source code.

```java
@Configuration
public class AppConfig {
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper(); // registered manually in the IoC container
    }
}
```

### Injecting Beans (`@Autowired`)

Spring resolves dependencies and injects them using the `@Autowired` annotation. There are three ways to do this:

#### A. Constructor Injection (Recommended)
Dependencies are provided through the class constructor.
- **Why it is recommended**: Beans are immutable; dependencies cannot be null (fails at startup if missing); easy to unit test by passing mocks in constructors.
- *Note*: Starting with Spring 4.x, if a class has a single constructor, the `@Autowired` annotation is optional.

```java
@Service
public class StudentService {
    private final StudentRepository studentRepository;

    // Autowired implicitly because there is only one constructor
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
}
```

#### B. Setter Injection
Dependencies are injected using setter methods. Useful for optional dependencies.
```java
private NotificationService notificationService;

@Autowired
public void setNotificationService(NotificationService notificationService) {
    this.notificationService = notificationService;
}
```

#### C. Field Injection
Dependencies are injected directly into fields via reflection.
- **Why it is discouraged**: It bypasses constructors; makes unit testing harder (requires reflection utilities to mock); violates encapsulation.
```java
@Autowired
private StudentRepository studentRepository; // Discouraged
```
