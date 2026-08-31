[← Back to Master README](../README.md)

# DTOs, Validations, & Exception Handling

This note covers how to build secure, robust, and professional REST APIs in Spring Boot using **Data Transfer Objects (DTOs)**, **Jakarta Bean Validation**, and **Global Exception Handling**.

---

## 1. The DTO Pattern

**Data Transfer Objects (DTOs)** are objects designed to transfer data between layers (specifically from the web controller to the service layer).

### Why use DTOs?
1. **Decoupling**: Decouples the API's external contract from the internal database schema. You can change database columns without breaking clients.
2. **Security (Over-posting Prevention)**: Prevents clients from sending and modifying fields they shouldn't (e.g., `id`, `createdAt`, `role`).
3. **Data Tailoring**: Entities represent database rows, whereas DTOs represent specific payloads (e.g., we don't want to receive `id` or `createdAt` during student creation, but we do want to return them in the response).

In our project, we have separate requests and responses:
- [CreateStudentRequestDTO.java](../src/main/java/in/anurag/crudSpingBootDemo/dto/CreateStudentRequestDTO.java)
- [CreateStudentResponseDTO.java](../src/main/java/in/anurag/crudSpingBootDemo/dto/CreateStudentResponseDTO.java)
- [UpdateStudentRequestDTO.java](../src/main/java/in/anurag/crudSpingBootDemo/dto/UpdateStudentRequestDTO.java)
- [UpdateStudentResponseDTO.java](../src/main/java/in/anurag/crudSpingBootDemo/dto/UpdateStudentResponseDTO.java)

---

## 2. Jakarta Bean Validation

We can validate client inputs declarative-style using the standard validation starter.

### Common Validation Annotations:
- **`@NotNull`**: Checks if the field is not null.
- **`@NotBlank`**: Checks if a string is not null, not empty, and contains at least one non-whitespace character.
- **`@Size(min, max)`**: Enforces string length bounds.
- **`@Min(value)` / `@Max(value)`**: Restricts numeric values.
- **`@Email`**: Validates format matches email standards.

#### Code Example:
In [CreateStudentRequestDTO.java](../src/main/java/in/anurag/crudSpingBootDemo/dto/CreateStudentRequestDTO.java):
```java
public class CreateStudentRequestDTO {
    @NotBlank(message = "Name cannot be null/Empty or blank")
    @Size(min = 2, max = 50, message = "Student name must be within 2 to 50 character long")
    private String name;

    @NotNull(message = "Age is required")
    @Min(value = 18, message = "Student must be atleast 18 years old")
    private int age;

    @NotBlank(message = "Student email cannot be blank")
    @Email(message = "Student Email must be valid")
    private String email;
}
```

### Activating Validation
In [StudentController.java](../src/main/java/in/anurag/crudSpingBootDemo/controller/StudentController.java), we apply the `@Valid` annotation to request bodies:
```java
@PostMapping
public ResponseEntity<CreateStudentResponseDTO> createStudent(@Valid @RequestBody CreateStudentRequestDTO request) {
    ...
}
```
If validation fails, Spring Boot automatically throws a `MethodArgumentNotValidException`.

---

## 3. Global Exception Handling

Instead of letting errors crash requests and return default server tracebacks, we intercept errors globally and return structured JSON responses.

### Key Annotations:
- **`@RestControllerAdvice`**: Applies to all controllers, acting as an interceptor for exceptions. Any exception thrown by controller methods is caught here.
- **`@ExceptionHandler`**: Marks a method inside the advice class to handle specific exceptions.

### Project Implementation:
Look at our [GlobalExceptionHandler.java](../src/main/java/in/anurag/crudSpingBootDemo/exception/GlobalExceptionHandler.java):

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 1. Handling Custom Business Exceptions
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponseDto> handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request) {
        ExceptionResponseDto response = new ExceptionResponseDto(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    // 2. Handling Input Validation Failures
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationExceptionResponseDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, String> fieldErrors = new HashMap<>();
        // Capture all field errors and messages
        ex.getBindingResult().getFieldErrors().forEach(error -> 
            fieldErrors.put(error.getField(), error.getDefaultMessage())
        );

        ValidationExceptionResponseDto response = new ValidationExceptionResponseDto(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "Validation Failed",
                request.getRequestURI(),
                fieldErrors
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
```

### Why this is Professional:
1. **Consistency**: All error payloads share the exact same JSON format (`timestamp`, `status`, `error`, `message`, `path`).
2. **Client-Friendly**: Validation errors map field-by-field (`email: "Student Email must be valid"`), helping frontend forms render validation highlights easily.
3. **Safety**: Sensitive stack traces are logged internally but never exposed in the HTTP response.


[← Back to Master README](../README.md)
