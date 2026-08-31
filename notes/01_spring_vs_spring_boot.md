[← Back to Master README](../README.md)

# Spring Framework & Spring Boot Introduction

This note covers the fundamentals of the **Spring Framework** and **Spring Boot**, comparing them, and explaining how to build a basic REST API.

---

## 1. Spring Framework vs. Spring Boot

### The Spring Framework
The **Spring Framework** is an open-source Java platform designed to simplify enterprise application development. It provides comprehensive infrastructure support, allowing developers to focus on application-level business logic rather than low-level plumbing.
- **Key Features**: Dependency Injection (DI), Inversion of Control (IoC), Aspect-Oriented Programming (AOP), data access framework, transaction management, and Spring MVC.
- **Pain Points**: Traditionally required massive amounts of configuration—either through extensive XML files or verbose Java Configuration classes. Configuring simple database connections, view templates, and web servers took hours.

### Spring Boot
**Spring Boot** is an extension of the Spring Framework. It doesn't replace Spring; instead, it acts as an opinionated wrapper built on top of it.
- **Goal**: To make it as easy as possible to build stand-alone, production-grade Spring-based applications that you can "just run."
- **How it solves Spring's pain points**:
  - **Opinionated Defaults**: Spring Boot automatically configures common beans based on jar dependencies on the classpath.
  - **Embedded Servers**: It embeds servers like Tomcat, Jetty, or Undertow directly into the application artifact (no need to deploy a `.war` file to a standalone web server).
  - **Starter Dependencies**: It groups related dependencies into "starters" (e.g., `spring-boot-starter-web`), removing the need to manage matching version numbers for different libraries.

| Feature | Spring Framework | Spring Boot |
| :--- | :--- | :--- |
| **Config Boilerplate** | High (XML/Java Config) | Very low (Auto-configuration) |
| **Server Requirement** | Standalone servlet container (Tomcat, Wildfly) | Embedded out-of-the-box (`spring-boot-starter-web`) |
| **Dependency Mgmt** | Manual version pairing | Starter BOMs (Bill of Materials) manage version compatibility |
| **CLI & Actuator** | No | Yes (built-in production monitoring & CLI tools) |

---

## 2. Bootstrapping Your First Spring Boot REST API

To build a REST API in Spring Boot, we utilize the **Spring Web** starter, which relies on Spring MVC under the hood.

### Essential Annotations

- **`@RestController`**: Composes `@Controller` and `@ResponseBody`. It marks the class as a web controller and ensures that returned data is automatically serialized into JSON or XML and written directly to the HTTP response body.
- **`@RequestMapping`**: Configures the base URI path mapping at the class level.
- **`@GetMapping` / `@PostMapping` / `@PutMapping` / `@DeleteMapping`**: Method-level mapping shortcuts for specific HTTP methods.

### Real-world Code Example

In our project, [StudentController.java](../src/main/java/in/anurag/crudSpingBootDemo/controller/StudentController.java) acts as the entry point for the REST API. Below is a simplified representation of how it is defined:

```java
package in.anurag.crudSpingBootDemo.controller;

import in.anurag.crudSpingBootDemo.dto.CreateStudentRequestDTO;
import in.anurag.crudSpingBootDemo.dto.CreateStudentResponseDTO;
import in.anurag.crudSpingBootDemo.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    // Constructor Injection (Loose Coupling)
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // HTTP GET request to fetch all students
    @GetMapping
    public ResponseEntity<List<CreateStudentResponseDTO>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudent());
    }

    // HTTP POST request to create a new student
    @PostMapping
    public ResponseEntity<CreateStudentResponseDTO> createStudent(@RequestBody CreateStudentRequestDTO request) {
        CreateStudentResponseDTO response = studentService.createStudent(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
```

### Request and Response Lifecycle
1. **HTTP Client** sends a Request: `POST /api/students` with a JSON payload.
2. **Tomcat Server** receives the request and forwards it to the `DispatcherServlet`.
3. **DispatcherServlet** uses `HandlerMapping` to find that `StudentController` has a method mapped to `POST /api/students`.
4. **Jackson library** (provided by Spring Boot auto-configuration) deserializes the JSON request body into the Java object `CreateStudentRequestDTO`.
5. **Controller** delegates to **Service** to handle logic.
6. **Controller** returns a `ResponseEntity` wrapped with a response body DTO and HTTP Status.
7. **DispatcherServlet** uses Jackson to serialize the response object back to a JSON string and sends the HTTP response back to the client.


[← Back to Master README](../README.md)
