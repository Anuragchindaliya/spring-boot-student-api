[← Back to Master README](../README.md)

# Spring XML Configuration (Legacy)

Before annotation-based configurations and Spring Boot, Spring applications were configured using XML files. While modern Spring Boot utilizes Java annotations, understanding XML configuration is critical for working with legacy codebases.

---

## 1. Declaring Beans in XML

XML configuration files declare beans inside a root `<beans>` element. A typical bean declaration requires an `id` (the identifier) and a `class` (fully qualified name).

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!-- Equivalent to @Component / @Bean -->
    <bean id="studentRepo" class="in.anurag.crudSpingBootDemo.repository.StudentRepositoryImpl"/>

</beans>
```

---

## 2. Dependency Injection in XML

Dependencies are wired together inside the XML definitions using either Constructor Injection or Setter Injection.

### A. Constructor Injection (`<constructor-arg>`)
Uses the `<constructor-arg>` element. You refer to another bean using the `ref` attribute, or inject raw values using `value`.

```xml
<bean id="studentService" class="in.anurag.crudSpingBootDemo.service.StudentService">
    <!-- Injecting the bean with ID 'studentRepo' into the constructor -->
    <constructor-arg ref="studentRepo"/>
</bean>
```

### B. Setter Injection (`<property>`)
Uses the `<property>` element. This requires the target bean to have a setter method conforming to JavaBean naming conventions (e.g., `setStudentRepository`).

```xml
<bean id="studentService" class="in.anurag.crudSpingBootDemo.service.StudentService">
    <!-- Invokes setStudentRepository(studentRepo) behind the scenes -->
    <property name="studentRepository" ref="studentRepo"/>
</bean>
```

---

## 3. Scopes, Lifecycle, and Autowiring in XML

### Bean Scopes
Determined by the `scope` attribute:
```xml
<bean id="taskExecutor" class="in.anurag.utils.TaskExecutor" scope="prototype"/>
```

### Lifecycle Methods
Initialization and destruction callbacks are defined using `init-method` and `destroy-method` attributes, matching method names in the Java class.
```xml
<bean id="dbManager" class="in.anurag.db.DBManager" 
      init-method="connect" 
      destroy-method="disconnect"/>
```

### Autowiring in XML
Spring XML supports implicit autowiring using the `autowire` attribute:
- `byName`: Matches property name with bean ID.
- `byType`: Matches property type with bean class.
- `constructor`: Autowires by constructor argument types.

```xml
<bean id="studentService" class="in.anurag.crudSpingBootDemo.service.StudentService" autowire="constructor"/>
```

---

## 4. XML vs. Java Configuration

Modern Spring development favors **Java Configuration** (`@Configuration` classes + `@Bean` annotations) or **Stereotype Annotations** (`@Component` + Component Scan).

| Feature | XML Configuration | Java Config / Stereotype Annotations |
| :--- | :--- | :--- |
| **Type Safety** | No (Fails at runtime for spelling mistakes in class names) | Yes (Compile-time checking by IDE and compiler) |
| **Readability** | Poor for large systems (XML files become massive) | High (Config lives in Java classes or near source code) |
| **Refactoring** | Hard (Renaming classes doesn't update XML strings automatically) | Easy (Standard IDE refactoring works seamlessly) |
| **Boilerplate** | High | Minimal (using component-scan) |

In modern Spring Boot applications, component scanning is enabled by default via the `@SpringBootApplication` annotation, scanning everything in its sub-packages automatically without any XML setup.


[← Back to Master README](../README.md)
