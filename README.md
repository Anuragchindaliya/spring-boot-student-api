# Spring Boot Learning Showcase & REST API Project

Welcome! This repository functions as a professional showcase demonstrating a complete, production-grade implementation of Spring Boot, Spring Framework, and Apache Maven concepts. 

The project contains a fully-functional **Student CRUD REST API** built with a 3-tier layered architecture, Jakarta validations, global exception handling, PostgreSQL integration, and profile-specific configuration strategies.

---

## 🛠️ Technology Stack
- **Language**: Java 21
- **Framework**: Spring Boot 3.3.x (Spring Core, MVC, Data JPA)
- **Validation**: Jakarta Bean Validation (Hibernate Validator)
- **Database**: PostgreSQL (with profile fallback setups)
- **Build Tool**: Apache Maven (Maven Wrapper `mvnw` included)

---

## 📚 Curriculum Roadmap & Code Mapping

Below is the complete 18-part curriculum map. Use this table to jump directly to the detailed conceptual notes or check the actual code implementation in this repository.

| Course Video / Concept | Detailed Conceptual Notes | Real-world Code Implementation |
| :--- | :--- | :--- |
| **#1 Spring Boot & Framework Intro** | [01_spring_vs_spring_boot.md](file:///Users/anurag/Desktop/crudSpingBootDemo/notes/01_spring_vs_spring_boot.md) | Standard Spring container initialization |
| **#2 First Spring Boot Application** | [01_spring_vs_spring_boot.md](file:///Users/anurag/Desktop/crudSpingBootDemo/notes/01_spring_vs_spring_boot.md) | [StudentController.java](file:///Users/anurag/Desktop/crudSpingBootDemo/src/main/java/in/anurag/crudSpingBootDemo/controller/StudentController.java) |
| **#3 Master Apache Maven** | [02_maven_basics.md](file:///Users/anurag/Desktop/crudSpingBootDemo/notes/02_maven_basics.md) | [pom.xml](file:///Users/anurag/Desktop/crudSpingBootDemo/pom.xml) |
| **#4 DI & IoC Explained** | [03_ioc_and_di.md](file:///Users/anurag/Desktop/crudSpingBootDemo/notes/03_ioc_and_di.md) | Constructor Injection inside [StudentService.java](file:///Users/anurag/Desktop/crudSpingBootDemo/src/main/java/in/anurag/crudSpingBootDemo/service/StudentService.java#L19-L23) |
| **#5 Beans, @Component, @Autowired** | [03_ioc_and_di.md](file:///Users/anurag/Desktop/crudSpingBootDemo/notes/03_ioc_and_di.md) | [NotificationController.java](file:///Users/anurag/Desktop/crudSpingBootDemo/src/main/java/in/anurag/crudSpingBootDemo/controller/NotificationController.java) |
| **#6 Circular Dependency & Scopes** | [04_circular_dependency_scopes_lifecycle.md](file:///Users/anurag/Desktop/crudSpingBootDemo/notes/04_circular_dependency_scopes_lifecycle.md) | Managed by IoC container configurations |
| **#7 Bean Lifecycle & @Lazy** | [04_circular_dependency_scopes_lifecycle.md](file:///Users/anurag/Desktop/crudSpingBootDemo/notes/04_circular_dependency_scopes_lifecycle.md) | `@PostConstruct` & `@PreDestroy` definitions |
| **#8 Spring XML Configuration** | [05_spring_xml_config.md](file:///Users/anurag/Desktop/crudSpingBootDemo/notes/05_spring_xml_config.md) | Comparison notes (Java Annotation-driven preferred) |
| **#9 Spring Boot Core & Auto Config** | [06_spring_boot_core_auto_config.md](file:///Users/anurag/Desktop/crudSpingBootDemo/notes/06_spring_boot_core_auto_config.md) | [CrudSpingBootDemoApplication.java](file:///Users/anurag/Desktop/crudSpingBootDemo/src/main/java/in/anurag/crudSpingBootDemo/CrudSpingBootDemoApplication.java) |
| **#10 properties, @Value, & Runners** | [07_properties_value_runners.md](file:///Users/anurag/Desktop/crudSpingBootDemo/notes/07_properties_value_runners.md) | `@Value` in [ServerInfoController.java](file:///Users/anurag/Desktop/crudSpingBootDemo/src/main/java/in/anurag/crudSpingBootDemo/controller/ServerInfoController.java#L12) |
| **#11 CRUD Project Architecture** | [09_crud_jpa_soft_delete.md](file:///Users/anurag/Desktop/crudSpingBootDemo/notes/09_crud_jpa_soft_delete.md) | 3-tier structure (Controllers, Services, Repos) |
| **#12 CRUD MySQL/Postgres JPA Methods** | [09_crud_jpa_soft_delete.md](file:///Users/anurag/Desktop/crudSpingBootDemo/notes/09_crud_jpa_soft_delete.md) | JpaRepository usage in [StudentRepository.java](file:///Users/anurag/Desktop/crudSpingBootDemo/src/main/java/in/anurag/crudSpingBootDemo/repository/StudentRepository.java) |
| **#13 Soft Delete Implementation** | [09_crud_jpa_soft_delete.md](file:///Users/anurag/Desktop/crudSpingBootDemo/notes/09_crud_jpa_soft_delete.md) | `deleted` flag filter in [StudentRepository.java](file:///Users/anurag/Desktop/crudSpingBootDemo/src/main/java/in/anurag/crudSpingBootDemo/repository/StudentRepository.java#L12-L13) |
| **#14 Servlets, Tomcat, & WAR files** | [08_web_architecture_tomcat.md](file:///Users/anurag/Desktop/crudSpingBootDemo/notes/08_web_architecture_tomcat.md) | Running on Embedded Tomcat Servlet Container |
| **#15 Spring MVC Architecture** | [08_web_architecture_tomcat.md](file:///Users/anurag/Desktop/crudSpingBootDemo/notes/08_web_architecture_tomcat.md) | DispatcherServlet Front-Controller mapping |
| **#16 DTOs & Jakarta Validations** | [10_dtos_validation_exception_handling.md](file:///Users/anurag/Desktop/crudSpingBootDemo/notes/10_dtos_validation_exception_handling.md) | `@Valid` in [CreateStudentRequestDTO.java](file:///Users/anurag/Desktop/crudSpingBootDemo/src/main/java/in/anurag/crudSpingBootDemo/dto/CreateStudentRequestDTO.java) |
| **#17 Exception Handling** | [10_dtos_validation_exception_handling.md](file:///Users/anurag/Desktop/crudSpingBootDemo/notes/10_dtos_validation_exception_handling.md) | [GlobalExceptionHandler.java](file:///Users/anurag/Desktop/crudSpingBootDemo/src/main/java/in/anurag/crudSpingBootDemo/exception/GlobalExceptionHandler.java) |
| **#18 Profiles & YAML Configurations** | [11_profiles_yaml.md](file:///Users/anurag/Desktop/crudSpingBootDemo/notes/11_profiles_yaml.md) | Dev/Prod `@Profile` in [NotificationServiceImpl.java](file:///Users/anurag/Desktop/crudSpingBootDemo/src/main/java/in/anurag/crudSpingBootDemo/service/NotificationServiceImpl.java) |

---

## 🏗️ 3-Tier Layered Architecture Design

The application follows the clean, maintainable 3-tier architecture:

```
[HTTP Client] ---> [StudentController] (REST API layer)
                           │
                           ▼
                  [StudentService]    (Business validation & DTO mapping)
                           │
                           ▼
                  [StudentRepository] (JPA data abstraction layer)
                           │
                           ▼
                     [PostgreSQL]     (Database persistence)
```

1. **Presentation Layer**: [StudentController.java](file:///Users/anurag/Desktop/crudSpingBootDemo/src/main/java/in/anurag/crudSpingBootDemo/controller/StudentController.java) receives raw payloads, enforces path variables, and issues appropriate HTTP response status codes.
2. **Service Layer**: [StudentService.java](file:///Users/anurag/Desktop/crudSpingBootDemo/src/main/java/in/anurag/crudSpingBootDemo/service/StudentService.java) enforces business constraints (e.g. email uniqueness checking) and translates between entity structures ([Student.java](file:///Users/anurag/Desktop/crudSpingBootDemo/src/main/java/in/anurag/crudSpingBootDemo/entity/Student.java)) and security DTO wrappers.
3. **Repository Layer**: [StudentRepository.java](file:///Users/anurag/Desktop/crudSpingBootDemo/src/main/java/in/anurag/crudSpingBootDemo/repository/StudentRepository.java) uses Spring Data JPA to translate method invocations into optimized SQL execution plans.

---

## 🚀 How to Run the Project

### 1. Prerequisites
- Java Development Kit (JDK) 21
- Maven installed locally (or run using `./mvnw` wrapper)
- A running PostgreSQL instance (credentials defined in [application-dev.properties](file:///Users/anurag/Desktop/crudSpingBootDemo/src/main/resources/application-dev.properties))

### 2. Run the Application
You can run the application locally using the standard Maven profile setup:

```bash
# Build and package the project (executes compile, test, package)
./mvnw clean package

# Run with Dev profile active (uses dummy notification service and local DB config)
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev

# Run with Prod profile active (runs real notification beans and production database configurations)
./mvnw spring-boot:run -Dspring-boot.run.profiles=prod
```

### 3. Verification & APIs

#### A. Fetch Server Name (`@Value` Showcase)
```bash
curl -X GET http://localhost:8080/server-info
```

#### B. Create a Student (Triggers DTO Validations)
```bash
curl -X POST http://localhost:8080/api/students \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Jane Doe",
    "age": 20,
    "email": "jane.doe@example.com",
    "rollNo": 101,
    "subject": "Computer Science"
  }'
```

#### C. Get All Students (Soft-Delete filtered)
```bash
curl -X GET http://localhost:8080/api/students
```

#### D. Soft Delete a Student
```bash
curl -X DELETE http://localhost:8080/api/students/1
```
*(Subsequent calls to GET /api/students will no longer list the student with ID 1, but the record remains in the database with `deleted = true`)*.
