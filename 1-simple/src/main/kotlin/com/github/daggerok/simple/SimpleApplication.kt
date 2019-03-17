package com.github.daggerok.simple

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SimpleApplication

fun main(args: Array<String>) {
  runApplication<SimpleApplication>(*args).registerShutdownHook()
}
