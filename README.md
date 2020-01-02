# Spring Data JDBC [![Build Status](https://travis-ci.org/daggerok/spring-data-jdbc-examples.svg?branch=master)](https://travis-ci.org/daggerok/spring-data-jdbc-examples)
Fast and efficient spring-data

## see simple and ddd submodules!

- [YouTube: Spring Data JDBC - Many to Many Relationships](https://www.youtube.com/watch?v=5rqlqon8xko)
- [Lombok value objects breaking changes fixes](https://stackoverflow.com/questions/48330613/objectmapper-cant-deserialize-without-default-constructor-after-upgrade-to-spri)
- [Spring Data JDBC Getting started](./simple/)
- [DDD app based on Spring Data JDBC](./ddd/)

<!-- we dont need that shit anymore... only if you wanna tests somethig with treboot...

## prepare

_start H2 database_

```bash
./mvnw -pl :h2 spring-boot:run
```

open [H2 web UI](http://127.0.0.1:8080/h2-console/)

-->

## increment project version

```bash
./mvnw -DgenerateBackupPoms=false build-helper:parse-version versions:set -DnewVersion=\${parsedVersion.majorVersion}.\${parsedVersion.minorVersion}.\${parsedVersion.nextIncrementalVersion}-SNAPSHOT
```

## check versions update

```bash
./mvnw versions:display-property-updates
```

links:

* [GitHub: schauder/talk-ddd-jdbc (DDD JDBC talk repo)](https://github.com/schauder/talk-ddd-jdbc/blob/master/src/main/java/de/schauderhaft/ddd/jdbc/LegoModel.java)
* [YouTube: Domain-Driven Design with Relational Databases Using Spring Data JDBC (DDD JDBC talk)](https://www.youtube.com/watch?v=GOSW911Ox6s)
* [YouTube: The New Kid on the Block: Spring Data JDBC](https://www.youtube.com/watch?v=AnIouYdwxo0)
* [Part 1: Spring Data JDBC - Getting started introduction](https://spring.io/blog/2018/09/17/introducing-spring-data-jdbc)
* [Part 2: Spring Data JDBC - References and Aggregates](https://spring.io/blog/2018/09/24/spring-data-jdbc-references-and-aggregates)
* [Publishing Events from Aggregate Roots](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#core.domain-events)
* [Domain-Driven Design and Spring](http://static.olivergierke.de/lectures/ddd-and-spring/)
* [YouTube: Spring Data JDBC - One to One & One to Many Relationships](https://www.youtube.com/watch?v=ccxBXDAPdmo)

other repositories to look:

- [GitHub: daggerok/spring-data-jdbc-example](https://github.com/daggerok/spring-data-jdbc-example)
- [GitHub: daggerok/spring-data-examples](https://github.com/daggerok/spring-data-examples)
