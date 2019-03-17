package com.github.daggerok.h2

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@SpringBootTest
@RunWith(SpringRunner::class)
class H2ApplicationTests {

  @Test
  fun contextLoads() {
    println("junit 4 test: $javaClass")
  }
}
