# Spring Boot Ecommerce Microservice

This application was generated using https://start.spring.io/

## Table of contents
* [General Info](#general-info)
* [Technologies](#technologies)
* [Environment Variables](#environment-variables)
* [Setup](#setup)
* [Features](#features)
* [References](#references)
* [Guides](#guides)
* [Additional Links](#additional-links)
* [Contact](#contact)

## General Info
Back-end service which provides ecommerce-related resources such as product and order endpoints using Spring Boot Reactive.

## Technologies
* Spring Boot
* PostgreSQL JDBC/R2DBC Driver
* Liquibase

## Environment Variables
Following table is a **mandatory** environment variables used in this project.

| Variable Name | Datatype | Description |
| --- | --- | --- |
| APP_HOST | String | Server host |
| APP_PORT | Number | Server port |
| APP_ENV | String | Server environment (value between 'dev' or 'prod') |
| DB_URL_R2DBC | String | Database URL for R2DBC driver |
| DB_URL_JDBC | String | Database URL for JDBC driver |
| DB_USERNAME | String | Database username |
| DB_PASSWORD | String | Database password |

## Setup
To launch your tests:
```
./mvnw clean test
```

To package your application:
```
./mvnw clean package
```

To run your application:
```
./mvnw clean spring-boot:run
```

To run liquibase migration
```
./mvnw clean liquibase:update
```

To run liquibase rollback. The rollback is using rollback tag. The tag will follow current project version.
```
./mvnw clean liquibase:rollback
```

## Features

### TODO
- [ ] Can create, update, delete product type resource.

## References
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.6.2/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.6.2/maven-plugin/reference/html/#build-image)
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/2.6.2/reference/htmlsingle/#production-ready)
* [Spring Data R2DBC](https://docs.spring.io/spring-boot/docs/2.6.2/reference/html/spring-boot-features.html#boot-features-r2dbc)
* [Liquibase Migration](https://docs.spring.io/spring-boot/docs/2.6.2/reference/htmlsingle/#howto-execute-liquibase-database-migrations-on-startup)

## Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)
* [Acessing data with R2DBC](https://spring.io/guides/gs/accessing-data-r2dbc/)

## Additional Links
These additional references should also help you:

* [R2DBC Homepage](https://r2dbc.io)

## Contact
Created by [Anas Juwaidi](mailto:anas.didi95@gmail.com)
