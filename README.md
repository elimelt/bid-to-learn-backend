# Draft of the backend for Bid To Learn

## Installation

### Requirements

- Java 17
- Maven 3.*
- PostgreSQL

### Steps

1. Clone the repository
2. Create a database in PostgreSQL with the following [schema](./schema.sql)
3. Create a file named `application.properties` in `src/main/resources` and fill it with the following content:

```
spring.datasource.url=jdbc:postgresql://<connection_string>
spring.datasource.username=<username>
spring.datasource.password=<password>
spring.jpa.hibernate.ddl-auto=update
springdoc.swagger-ui.url=/openapi.json
```

4. Run the application with `mvn spring-boot:run`, or build it with `mvn package` and run the jar file in `target/` with `java -jar <jar_file_name>`

5. The application will be running on `localhost:8080`. To view the API documentation, go to `localhost:8080/swagger-ui.html`


