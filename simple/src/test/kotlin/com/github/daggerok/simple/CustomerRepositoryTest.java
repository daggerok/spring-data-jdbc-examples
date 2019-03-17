package com.github.daggerok.simple;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@Log4j2
@DataJdbcTest
@ExtendWith(SpringExtension.class)
class CustomerRepositoryTest {

  @Autowired
  CustomerRepository customerRepository;

  @Test
  void testSave() {
    Customer newCustomer = Customer.createForName("ololo trololo");
    Customer savedCustomer = customerRepository.save(newCustomer);

    log.info("savedCustomer {}", savedCustomer);
    assertThat(savedCustomer.getName()).isEqualTo(savedCustomer.getName());
    assertThat(savedCustomer.getId()).isNotNull();
  }

  @Test
  void testClone() {
    Customer customer1 = customerRepository.save(Customer.createForName("ololo trololo"));
    Long oldId = customer1.getId();
    log.info("1st: {}", customer1);

    // let's clone entity by setting it's id to null!
    Customer customer2 = customerRepository.save(customer1.withId(null));
    Long newId = customer2.getId();
    log.info("2nd: {}", customer2);

    assertThat(customer2).isEqualTo(customer1);
    assertThat(newId).isNotEqualTo(oldId);
  }
}
