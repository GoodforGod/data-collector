# Oracle Database Aggregator

Aggregates data from multiple databases and sync all in Oracle database.

## Build & Run locally

Install Oracle Database.

Download repository.

Download [oJDBC (ojdbc7.jar)](https://www.oracle.com/technetwork/database/features/jdbc/jdbc-drivers-12c-download-1958347.html) and put it in */driver* folder inside project root.
Rename file to *ojdbc7-12.1.0.2.jar*

Build with:
```bash
./gradlew bootJar
```

Set environment variables:
* ORA_USER - database user
* ORA_PASS - database password
* ORA_URL - database url (like *jdbc:oracle:thin:@localhost:32769:ORCLCDB*)

Execute (Located in /build/libs):
```bash
java -jar oracle-aggregator-1.0.0.jar
```

## Build & Run with Docker

Order of execution: *Build -> Up -> Stop*

Build
```bash
docker-compose build
```

Run (add *-d* to use detached mode or it will be interactive)
```bash
docker-compose up
```

Stop
```bash
docker-compose stop
```

### Endpoints

All server endpoints at - *localhost:8080/swagger-ui.html*

Learn more about [swagger ui](https://swagger.io/tools/swagger-ui/)

There are specific *load* & *load test* endpoints for each database with specific name for such database.

### Oracle Common Schema
Some tables changed, check DB for correct schema.

![](doc/schema_common.png)

### JSON Example

[JSON schema](https://pastebin.com/nsycLfgE)