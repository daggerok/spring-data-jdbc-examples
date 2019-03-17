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
  void test() {
    log.info("junit jupiter test");
    Customer newCustomer = Customer.createForName("ololo trololo");
    Customer savedCustomer = customerRepository.save(newCustomer);
    assertThat(savedCustomer.getName()).isEqualTo(savedCustomer.getName());
    assertThat(savedCustomer.getId()).isNotNull();
    log.info("savedCustomer {}", savedCustomer);
  }
}
