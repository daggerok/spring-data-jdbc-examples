package com.github.daggerok.h2

import org.h2.tools.Server
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.sql.DriverManager
import java.util.concurrent.TimeUnit

@SpringBootApplication
class H2Application

fun main(args: Array<String>) {
  val tcpServer = Server.createTcpServer(
      "-tcp",
      "-tcpAllowOthers",
      "-tcpDaemon",
      "-tcpPort", "9092"
  )
  tcpServer.start()
  Class.forName("org.h2.Driver")
  val applicationContext = runApplication<H2Application>(*args)
  val url = applicationContext.environment.getProperty("spring.datasource.url")
  val username = applicationContext.environment.getProperty("spring.datasource.username")
  val password = applicationContext.environment.getProperty("spring.datasource.password", "")
  while (!tcpServer.isRunning(true)) TimeUnit.SECONDS.sleep(1)
  DriverManager.getConnection(url, username, password)
  applicationContext.registerShutdownHook()
}
