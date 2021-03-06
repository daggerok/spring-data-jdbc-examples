# AggregateRoot and Domain Events

_With Spring Data (and Spring Data JDBC supports it as well) we can very easy implement some DDD concepts in a 1-2-3 steps_

## NOTE: this is not a really event sourced app

Aggregate Root is something we may update and due to DDD it can emit (Domain Eventsourced) events on different operations (so called commands)...

## 1

_To make our Customer entity ([from simple project](https://github.com/daggerok/spring-data-jdbc-examples/blob/master/simple/src/main/kotlin/com/github/daggerok/simple/Customer.java)) an aggregate is easy:_

```java
class Customer extends AbstractAggregateRoot<Customer> { /* ... */ }
```

NOTE: it's extends AbstractAggregateRoot with itself as a generic typed parameter...

## 2

_Secondly (in our root aggregate) we need to register event, when something important from prespective our domain (business) is happened..._

For erxample on create customer Command, each time when new customer was created, we will register customer created event, whuch eventually will be emitted by Spring Data after entity successfully persisted:

```java
  public static Customer createForName(String name) {
    // Customer customer = ...
    CustomerCreatedEvent event = new CustomerCreatedEvent(customer);
    customer.registerEvent(event);
    return customer;
  }
```

where `CustomerCreatedEvent` definition is very simple:

```java
import org.springframework.context.ApplicationEvent;

public class CustomerCreatedEvent extends ApplicationEvent {
  public CustomerCreatedEvent(Object source) {
    super(source);
  }
}
```

NOTE: it's simple spring application event

## 3

_Lastly let's build statistics API to show how many customers with same name where actually created..._

```java
@RestController
public class StatisticsResource {

  Map<String, AtomicLong> statistics = new ConcurrentHashMap<>();

  @GetMapping("/statistics")
  public Map<String, AtomicLong> getStatistics() {
    return statistics;
  }

  @EventListener
  public void on(CustomerCreatedEvent event) {
    Customer newCustomer = (Customer) event.getSource();
    updateStatisticsFor(newCustomer);
  }

  void updateStatisticsFor(Customer newCustomer) {
    String name = newCustomer.getName();
    statistics.putIfAbsent(name, new AtomicLong(0));
    AtomicLong counter = statistics.get(name);
    counter.incrementAndGet();
    statistics.put(name, counter);
  }
}
```

## build run and test

so lets's run app and after let's create few customers:

```bash
http :8002 name=test
http :8002 name=test
http :8002 name="test 2"
```

now we can check if statistics is working as expected:

```bash
http :8002/statistics
HTTP/1.1 200 OK
Content-Length: 21
Content-Type: application/json;charset=UTF-8
# output
```

```json
{
    "test": 2,
    "test 2": 1
}
```

## in addition

To make our app consistent after reboot, let's introduce statistics reconstruction in `StatisticsResource` class:

```java
@RestController
public class StatisticsResource {

  private final CustomerRepository customerRepository;

  public StatisticsResource(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }
  
  @PostConstruct
  public void reconstruct() {
    StreamSupport.stream(customerRepository.findAll().spliterator(), true)
                 .forEach(this::updateStatisticsFor);
  }
  // rest without caches...
}
```

And we are done ;)

## TODO: additional resources to read

* [Domain Driven Design with relational Databases and Spring Data JDBC.](http://blog.schauderhaft.de/talk-ddd-jdbc/talk.html#/)
* [Effective Aggregate Design Part I](http://dddcommunity.org/wp-content/uploads/files/pdf_articles/Vernon_2011_1.pdf)
* [Effective Aggregate Design Part II](http://dddcommunity.org/wp-content/uploads/files/pdf_articles/Vernon_2011_2.pdf)
* [Effective Aggregate Design Part III](http://dddcommunity.org/wp-content/uploads/files/pdf_articles/Vernon_2011_3.pdf)
* [Spring Data JDBC reference](https://docs.spring.io/spring-data/jdbc/docs/current/reference/html/#jdbc.repositories)
* [spring.io/blog introducing-spring-data-jdbc](https://spring.io/blog/2018/09/17/introducing-spring-data-jdbc)
* [spring.io/blog spring-data-jdbc-references-and-aggregates](https://spring.io/blog/2018/09/24/spring-data-jdbc-references-and-aggregates)
* [Advancing Enterprise DDD](http://scabl.blogspot.com/p/advancing-enterprise-ddd.html)
* [The Vietnam of Computer Science](http://blogs.tedneward.com/post/the-vietnam-of-computer-science/)
* [GitHub: schauder/talk-ddd-jdbc](https://github.com/schauder/talk-ddd-jdbc)
