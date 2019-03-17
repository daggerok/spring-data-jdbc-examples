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
   @Test
   void testClone() {
     Customer customer1 = customerRepository.save(Customer.createForName("ololo trololo"));
     Customer customer2 = customerRepository.save(customer1.withId(null));
     assertThat(customer2.getId()).isNotEqualTo(customer1.getId());
   }
 ```
 
NOTE: as you can see generated IDs are different

## instances are not same even if you are loaded them twice!

```java
  @Test
  void testNotSame() {
    Long id = customerRepository.save(Customer.createForName("test")).getId();
    Optional<Customer> first = customerRepository.findById(id);
    Optional<Customer> second = customerRepository.findById(id);

    assertThat(first).isNotSameAs(second);
  }
```

NOTE: as you can see, each fetch from DB creates new java object instance

## one to one

- see [java](https://github.com/daggerok/spring-data-jdbc-examples/tree/master/simple/src/main/kotlin/com/github/daggerok/simple/onetoone)
- according [sql](https://github.com/daggerok/spring-data-jdbc-examples/blob/master/simple/src/main/resources/schema.sql#L21)
- and [tests](https://github.com/daggerok/spring-data-jdbc-examples/blob/master/simple/src/test/kotlin/com/github/daggerok/simple/SimpleJavaApplicationTests.java#L21)
- or [app](https://github.com/daggerok/spring-data-jdbc-examples/tree/master/simple/src/main/kotlin/com/github/daggerok/simple/onetoone/OneToOneRunner.java)

## one referencing to one

- see [java](https://github.com/daggerok/spring-data-jdbc-examples/tree/master/simple/src/main/kotlin/com/github/daggerok/simple/ref)
- according [sql](https://github.com/daggerok/spring-data-jdbc-examples/blob/master/simple/src/main/resources/schema.sql#L35)
- and [tests](https://github.com/daggerok/spring-data-jdbc-examples/blob/master/simple/src/test/kotlin/com/github/daggerok/simple/ref/BookTest.java#L22)
