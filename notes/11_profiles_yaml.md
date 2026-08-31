[← Back to Master README](../README.md)

# Profiles & YAML Configurations

This note covers the usage of **Spring Profiles** to manage environment-specific configurations and compares standard `.properties` with `.yml` configuration formats.

---

## 1. Spring Profiles

Profiles are a core feature of Spring that allow you to segregate parts of your application configuration and make it available only in specific environments (e.g., Development, Staging, Production).

### Profile-specific Files
Spring Boot automatically searches for files conforming to `application-{profile}.properties` or `application-{profile}.yml` and overrides properties found in the main `application.properties` file.

In our codebase:
- [application.properties](../src/main/resources/application.properties): Contains global defaults.
- [application-dev.properties](../src/main/resources/application-dev.properties): Database credentials and logging configurations for local development.
- [application-staging.properties](../src/main/resources/application-staging.properties): Staging environment database configuration.
- [application-prod.properties](../src/main/resources/application-prod.properties): Secure PostgreSQL database connections and optimized pool configurations for production.

### Activating Profiles
You can activate profiles in several ways:

1. **In Configuration File**:
   ```properties
   spring.profiles.active=dev
   ```
2. **As a JVM Command Line Parameter**:
   ```bash
   java -jar app.jar -Dspring.profiles.active=prod
   ```
3. **Using OS Environment Variables**:
   ```bash
   export SPRING_PROFILES_ACTIVE=staging
   ```

---

## 2. Profile-Specific Beans (`@Profile`)

You can conditionally load beans into the Spring container depending on the active profile by utilizing the `@Profile` annotation.

### Codebase Demonstration
We declare [NotificationService.java](../src/main/java/in/anurag/crudSpingBootDemo/service/NotificationService.java) to demonstrate this:

1. **Mock Service for Local Work**:
   In [DummyNotificationServiceImpl.java](../src/main/java/in/anurag/crudSpingBootDemo/service/DummyNotificationServiceImpl.java), we annotate the class to load on `dev`, `staging`, or when no profile is selected (`default`):
   ```java
   @Service
   @Profile({"dev", "default", "staging"})
   public class DummyNotificationServiceImpl implements NotificationService {
       @Override
       public String send() {
           return "Here is dummy notification";
       }
   }
   ```

2. **Real Service for Production**:
   In [NotificationServiceImpl.java](../src/main/java/in/anurag/crudSpingBootDemo/service/NotificationServiceImpl.java), we restrict the bean to the `prod` profile:
   ```java
   @Service
   @Profile("prod")
   public class NotificationServiceImpl implements NotificationService {
       @Override
       public String send(){
           return "Here is a notification";
       }
   }
   ```

When [NotificationController](../src/main/java/in/anurag/crudSpingBootDemo/controller/NotificationController.java) receives an injection request for `NotificationService`, Spring checks the active profile and injects the corresponding bean.

---

## 3. Properties vs. YAML Syntax

Spring Boot supports YAML (`.yml`) files as an alternative to standard properties (`.properties`) files. YAML is highly readable and hierarchically structured, reducing repeating prefixes.

### Comparative Example

#### A. Standard Properties Format (`.properties`)
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/students_db
spring.datasource.username=postgres
spring.datasource.password=secret
server.port=8080
```

#### B. Equivalent YAML Format (`.yml` / `.yaml`)
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/students_db
    username: postgres
    password: secret
server:
  port: 8080
```

### Key Differences:
- **Structure**: YAML uses indentation-based hierarchies, whereas properties files use dot-notated key-value flat structures.
- **Redundancy**: YAML eliminates repeating prefixes (like `spring.datasource`).
- **Support**: While properties files are standard and supported by standard Java configuration utilities, YAML files require SnakeYAML library (which is included automatically in all Spring Boot starter dependencies).


[← Back to Master README](../README.md)
