[← Back to Master README](file:///Users/anurag/Desktop/crudSpingBootDemo/README.md)

# Apache Maven Foundations

Apache Maven is a software project management and build automation tool used primarily for Java projects. It provides a uniform build system, a standard directory layout, and dependency management.

---

## 1. The Project Object Model (POM)

The **POM** (`pom.xml`) is the fundamental unit of work in Maven. It is an XML file containing information about the project and configuration details used by Maven to build the project.

### POM Elements

1. **Coordinates**: Uniquely identify the project in a Maven repository.
   - `<groupId>`: Represents the organization or group (e.g., `in.anurag`).
   - `<artifactId>`: The name of the project artifact/jar (e.g., `crudSpingBootDemo`).
   - `<version>`: The version of the project (e.g., `0.0.1-SNAPSHOT`).
2. **Properties**: Define reusable variables (e.g., `<java.version>21</java.version>`).
3. **Dependencies**: List of external libraries required by the project.
4. **Plugins**: Extensions to Maven to perform specific build tasks (e.g., packaging an executable jar).

Look at our project's [pom.xml](file:///Users/anurag/Desktop/crudSpingBootDemo/pom.xml) for a live example of these concepts!

---

## 2. Dependency Management & Scopes

When you declare a dependency, Maven automatically downloads it from the Maven Central Repository and handles **transitive dependencies** (the libraries that your libraries depend on).

### Dependency Scopes

The scope of a dependency controls its visibility in different classpaths (compilation, testing, execution, etc.):

- **`compile`** (Default): Available in all classpaths. Packaged into the final artifact.
- **`provided`**: Needed for compiling, but expected to be provided by the runtime environment (e.g., a servlet container). Not packaged.
- **`runtime`**: Not needed for compilation, but required for execution (e.g., JDBC driver implementations like PostgreSQL). Packaged.
- **`test`**: Only needed for compiling and running test cases (e.g., JUnit, Mockito). Not packaged.
- **`system`**: Similar to `provided` but requires an explicit path to a local jar file. (Generally avoided).
- **`import`**: Used only within a `<dependencyManagement>` element to import dependency definitions from a BOM (Bill of Materials).

#### Project Example from `pom.xml`:
```xml
<!-- PostgreSQL is marked 'runtime' because we compile against standard JPA classes, but need the driver at runtime -->
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <scope>runtime</scope>
</dependency>

<!-- Validation Starter is marked 'compile' (implicitly) because our DTOs import Validation classes directly -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
    <scope>compile</scope>
</dependency>
```

---

## 3. The Maven Build Lifecycle

Maven is based around the concept of a build lifecycle. Running a phase executes all preceding phases in that lifecycle.

There are three built-in lifecycles: **clean**, **default** (build), and **site** (documentation).

### The Default Lifecycle
This handles the compilation and packaging of your application. The key phases are:

1. **`validate`**: Validate the project is correct and all necessary information is available.
2. **`compile`**: Compile the source code of the project.
3. **`test`**: Run tests using a suitable unit testing framework.
4. **`package`**: Take the compiled code and package it in its distributable format (e.g., JAR, WAR).
5. **`verify`**: Run integration tests to ensure quality criteria are met.
6. **`install`**: Install the package into the local repository (`~/.m2/repository`) for use as a dependency in other local projects.
7. **`deploy`**: Copy the final package to the remote repository for sharing with other developers.

#### The Clean Lifecycle
- **`clean`**: Removes the `target` directory where all compiled classes and packaged files are generated, ensuring a fresh build.

---

## 4. Plugins & The Spring Boot Plugin

Maven's core features are executed by plugins.
In our [pom.xml](file:///Users/anurag/Desktop/crudSpingBootDemo/pom.xml#L65-L70), we declare the **`spring-boot-maven-plugin`**:

```xml
<plugin>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-maven-plugin</artifactId>
</plugin>
```

### Why do we need the Spring Boot Maven Plugin?
1. **Repackaging**: It repackages standard jars into a **Fat JAR** (or executable archive) containing all transitively resolved dependencies and an embedded web server.
2. **Execution**: It allows running the Spring Boot application directly using the command `mvn spring-boot:run`.
3. **Build Information**: It can generate build information details accessible via endpoints.


[← Back to Master README](file:///Users/anurag/Desktop/crudSpingBootDemo/README.md)
