package com.github.daggerok.simple;

import com.github.daggerok.simple.onetoone.OneToOneAggregateRoot;
import com.github.daggerok.simple.onetoone.OneToOneRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@DataJdbcTest
@ExtendWith(SpringExtension.class)
class SimpleJavaApplicationTests {

  @Autowired
  OneToOneRepository oneToOneRepository;

  @Test
  void test() {
    OneToOneAggregateRoot saved = oneToOneRepository.save(new OneToOneAggregateRoot());
    System.out.println("saved = " + saved);
    assertThat(saved.getId()).isNotNull();
    assertThat(saved.getNames()).isNull();

    OneToOneAggregateRoot savedAgain = oneToOneRepository.save(saved.withName("ololo"));
    System.out.println("savedAgain = " + savedAgain);
    assertThat(savedAgain.getId()).isEqualTo(saved.getId());
    assertThat(savedAgain.getNames())
        .isNotNull()
        .hasSize(1);

    OneToOneAggregateRoot andAgain = oneToOneRepository.save(saved.withName("trololo")
                                                                  .withName("olololo-trololololo"));
    System.out.println("andAgain = " + andAgain);
    assertThat(andAgain.getNames()).hasSize(3);
  }
}
