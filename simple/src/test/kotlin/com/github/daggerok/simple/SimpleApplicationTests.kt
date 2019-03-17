package com.github.daggerok.simple

import com.github.daggerok.simple.onetoone.OneToOneAggregateRoot
import com.github.daggerok.simple.onetoone.OneToOneRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@SpringBootTest
@RunWith(SpringRunner::class)
class SimpleApplicationTests {

  @Autowired
  private lateinit var oneToOneRepository: OneToOneRepository

  @Test
  fun contextLoads() {
    val saved = oneToOneRepository.save(OneToOneAggregateRoot())
    println("saved: $saved")
    assertThat(saved.id).isNotNull()
    assertThat(saved.names).isNull()

    var savedAgain = oneToOneRepository.save(saved.withName("ololo"))
    println("savedAgain: $savedAgain")
    assertThat(savedAgain.id).isEqualTo(saved.id)
    assertThat(savedAgain.names)
        .isNotNull()
        .hasSize(1)

    var andAgain = oneToOneRepository.save(saved
        .withName("trololo")
        .withName("olololo-trololololo"))
    println("andAgain: $andAgain")
    assertThat(andAgain.names).hasSize(3)
  }
}
