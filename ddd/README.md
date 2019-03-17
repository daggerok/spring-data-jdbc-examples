# AggregateRoot and Domain Events

_With Spring Data we can very easy implement DDD concepts. Spring Data JDBC supports it as well_

Aggregate Root is something we may update and due to DDD it can emit (Domain Eventsourced) events on different operations (so called Commands)...

## 1

_To make our Customer entity (from simple project) an aggregate is easy as 1-2-3_

```java
class Customer extends AbstractAggregateRoot<Customer> { ... }
```

NOTE: it's extends AbstractAggregateRoot with itself generic parameter...

## 2

_Secondly we need to register event when something important from prespective our domain (or actually business) is happened..._

For erxample here we emitting customer created event each time when new customer was created:

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
    Customer customer = (Customer) event.getSource();
    String name = customer.getName();
    statistics.putIfAbsent(name, new AtomicLong(0));
    AtomicLong counter = statistics.get(name);
    counter.incrementAndGet();
    statistics.put(name, counter);
  }
}
```

## build run and test

so lets's create few customers:

```bash
http :8002 name=test
http :8002 name=test
http :8002 name='test 2'
```

now check if statistics is works as expected:

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

And we are done ;)
