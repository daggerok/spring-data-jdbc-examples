package com.github.daggerok.ddd.domain;

import com.github.daggerok.ddd.events.CustomerCreatedEvent;
import lombok.Value;
import lombok.experimental.Wither;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.time.LocalDate;

@Value
@Wither
public class Customer extends AbstractAggregateRoot<Customer> {

  @Id
  Long id;
  String name;
  LocalDate date;
  Gender gender;

  public static Customer createForName(String name) {
    Customer customer = new Customer(null, name, LocalDate.now(), null);
    CustomerCreatedEvent event = new CustomerCreatedEvent(customer);
    customer.registerEvent(event);
    return customer;
  }
}
