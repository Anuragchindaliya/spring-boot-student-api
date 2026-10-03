[← Back to Master README](../README.md)

# Hibernate Fundamentals: CRUD & Entity Mapping

This note introduces **Object-Relational Mapping (ORM)**, the distinction between **JPA** (the specification) and **Hibernate** (the implementation), core JPA entity mapping annotations, and primary key generation strategies.

---

## 1. What is an ORM & The Impedance Mismatch?

In object-oriented programming (Java), data is structured in interconnected **objects** with inheritance, polymorphism, and references. In relational databases (PostgreSQL, MySQL), data is stored in **flat two-dimensional tables** linked by foreign keys.

This conceptual disconnect is called the **Object-Relational Impedance Mismatch**:
- **Identity Mismatch**: Java uses memory reference (`==`) and `equals()`; SQL uses primary keys.
- **Association Mismatch**: Java uses unidirectional references (`student.getAddress()`); SQL uses bidirectional foreign keys.
- **Inheritance Mismatch**: Java supports class hierarchies; standard SQL tables have no concept of inheritance.

An **ORM (Object-Relational Mapping)** framework automatically translates between Java objects and database table rows.

```mermaid
graph LR
    subgraph Java Application Model
        O[Student Object<br/>- Long id<br/>- String name<br/>- String email<br/>- Status status]
    end

    subgraph Hibernate ORM Layer
        ORM[Hibernate Engine<br/>Translates Annotations<br/>Generates SQL Statements]
    end

    subgraph Relational Database
        T[Table: students<br/>| id (BIGINT PK) |<br/>| name (VARCHAR) |<br/>| email (VARCHAR) |<br/>| status (VARCHAR) |]
    end

    O <-->|Automatic Mapping| ORM
    ORM <-->|JDBC SQL Execution| T
```

---

## 2. JPA vs. Hibernate

- **JPA (Jakarta Persistence API)**: A **specification** (a collection of interfaces, rules, and annotations like `@Entity`, `@Id`, `EntityManager`) standardizing ORM in Java. JPA contains no executable logic.
- **Hibernate**: The most popular **concrete implementation** of the JPA specification. Spring Boot automatically uses Hibernate as its default JPA provider under `spring-boot-starter-data-jpa`.

---

## 3. Core JPA Annotations

| Annotation | Purpose | Example |
| :--- | :--- | :--- |
| **`@Entity`** | Declares that the class is a JPA entity mapped to a database table. | `@Entity` |
| **`@Table`** | Customizes the target table name, catalog, and schema. | `@Table(name = "tbl_students")` |
| **`@Id`** | Marks the field as the primary key. | `@Id` |
| **`@GeneratedValue`** | Configures auto-generation strategy for primary keys. | `@GeneratedValue(strategy = GenerationType.IDENTITY)` |
| **`@Column`** | Customizes column name, nullability, uniqueness, and length. | `@Column(name = "email_address", nullable = false, unique = true)` |
| **`@Enumerated`** | Specifies how Java Enums are stored in the database. | `@Enumerated(EnumType.STRING)` (Always use `STRING`, never `ORDINAL`) |
| **`@Transient`** | Tells JPA to ignore this field; no database column is created. | `@Transient private String temporaryToken;` |
| **`@CreationTimestamp`** | Hibernate automatically populates creation timestamp on insert. | `@CreationTimestamp private LocalDateTime createdAt;` |
| **`@UpdateTimestamp`** | Hibernate updates timestamp on every entity modification. | `@UpdateTimestamp private LocalDateTime updatedAt;` |

---

## 4. Primary Key Generation Strategies

When using `@GeneratedValue`, you specify a `GenerationType`:

```
1. IDENTITY  ──► Uses database Auto-Increment column (PostgreSQL SERIAL / MySQL AUTO_INCREMENT).
2. SEQUENCE  ──► Uses an independent DB Sequence object (Recommended for PostgreSQL / Oracle).
3. TABLE     ──► Uses a dedicated database table to simulate sequences (Slow, avoid).
4. AUTO      ──► Lets Hibernate choose strategy based on database dialect.
5. UUID      ──► Generates 128-bit universally unique identifiers (GenerationType.UUID).
```

> [!TIP]
> **Performance Note**: `GenerationType.IDENTITY` disables Hibernate's batch insert optimizations because the entity must be inserted immediately into the database to obtain the generated ID. `GenerationType.SEQUENCE` allows pre-allocating IDs, enabling true JDBC batching.

---

## 5. Complete Entity Example

Below is a complete JPA entity demonstrating standard mappings:

```java
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "students", indexes = {
    @Index(name = "idx_student_email", columnList = "email", unique = true)
})
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false, length = 100)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private Integer age;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StudentStatus status = StudentStatus.ACTIVE;

    @Transient
    private String sessionToken; // Ignored by Hibernate

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Constructors, Getters, and Setters...
}
```

---

## 6. Hibernate DDL Auto: `ddl-auto` Options

Spring Boot controls automatic database schema creation via `spring.jpa.hibernate.ddl-auto`:

- **`none`**: No action. The database schema must already exist. (**Mandatory for Production**).
- **`validate`**: Validates that Java entity mappings match the database schema without altering tables.
- **`update`**: Updates the existing schema (adds new columns/tables, but never drops existing columns).
- **`create`**: Drops all tables and recreates the schema on application startup.
- **`create-drop`**: Drops and creates tables on startup, then drops everything when the application shuts down.

> [!CAUTION]
> **Production Safety Rule**:
> Never use `ddl-auto=update` or `create` in production environments! An accidental restart or field rename can result in permanent data loss. Always use database migration tools such as **Flyway** or **Liquibase** in production.

---

[← Back to Master README](../README.md)
