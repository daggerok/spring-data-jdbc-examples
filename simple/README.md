# Spring Data JDBC it's easy

## Getting started

- no lazy loading
- no caching
- no proxies
- no deferred flush - as soon you saved entity, you can query it immediately!

- not required no arg constructor
- not setters/getters required - you can use Withers (see for example according lombok annotation)

- aggregate root needs spring `@Id` annotation (not JPA one!)

## Clone Entity
 
 If you know JPA, you probably know how hard is to clone JPA entity to do not mess anything...
 But with Spring Data JDBC is easy! Just set entity id to null
 
 ```java
 
 ```
