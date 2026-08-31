[← Back to Master README](../README.md)

# Properties, @Value, & Runner Interfaces

This note explains how Spring Boot manages externalized configuration using properties and runs startup code using Runner interfaces.

---

## 1. Externalized Configuration

Spring Boot allows you to externalize your configuration so you can work with the same application code in different environments (e.g., development, testing, production). Configuration can be written in `application.properties` or `application.yml` files.

### Configuration Source Precedence (Highest to Lowest):
1. Command-line arguments (e.g., `--server.port=8081`).
2. Java System Properties (`-Dserver.port=8081`).
3. OS Environment Variables (e.g., `SPRING_APPLICATION_JSON='{"server":{"port":8081}}'`).
4. Profile-specific application properties (e.g., `application-dev.properties`).
5. Application properties packaged inside the jar (`application.properties`).

---

## 2. Injecting Properties using `@Value`

The `@Value` annotation is used to inject property values, environment variables, or expressions into fields of Spring-managed beans.

### Basic Property Injection
In our project, [ServerInfoController.java](../src/main/java/in/anurag/crudSpingBootDemo/controller/ServerInfoController.java) uses `@Value` to fetch the application name defined in properties:

```java
@RestController
@RequestMapping("/server-info")
public class ServerInfoController {

    @Value("${spring.application.name}")
    private String applicationName;

    @GetMapping
    public ResponseEntity<String> getServerInfo() {
        return ResponseEntity.ok(applicationName);
    }
}
```

### Providing Default Values
If a property is not defined, the application will throw a `BeanCreationException` during startup. You can prevent this by specifying a default value using a colon `:`:

```java
@Value("${server.port:8080}") // Injects port, or defaults to 8080 if not found
private int port;
```

### Spring Expression Language (SpEL)
`@Value` can also evaluate expressions using the `#{...}` syntax:
```java
@Value("#{systemProperties['user.home']}") // Injects the Java system property user.home
private String userHome;

@Value("#{2 * 10}") // Injects the evaluation of the expression (20)
private int limit;
```

### `@Value` vs. `@ConfigurationProperties`
While `@Value` is great for simple properties, **`@ConfigurationProperties`** is preferred for group-structured, type-safe configurations.

```java
@ConfigurationProperties(prefix = "database")
public class DbProperties {
    private String url;
    private String username;
    private String password;
    // Getters and setters...
}
```

---

## 3. The Runner Interfaces (Startup Hooks)

Often, you need to execute some logic (e.g., seeding a database, starting background schedulers, loading cache data) immediately after the Spring Boot application starts up. Spring Boot provides two interfaces for this:

### A. `CommandLineRunner`
Accepts raw `String... args` arguments.

```java
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSeeder implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("CommandLineRunner: Seeding initial data... arguments: " + String.join(", ", args));
    }
}
```

### B. `ApplicationRunner`
Accepts `ApplicationArguments`, which parses options like `--port=8080` into structured keys and values.

```java
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class CacheLoader implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("ApplicationRunner: Loading caches...");
        if (args.containsOption("seed-cache")) {
            System.out.println("Seeding cache option is active!");
        }
    }
}
```

### Controlling Execution Order (`@Order`)
If you have multiple runners, you can control their execution order using the `@Order` annotation or the `Ordered` interface. Lower values run first.

```java
@Component
@Order(1) // Runs first
public class FirstRunner implements CommandLineRunner { ... }

@Component
@Order(2) // Runs second
public class SecondRunner implements CommandLineRunner { ... }
```


[← Back to Master README](../README.md)
