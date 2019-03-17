package com.github.daggerok.ddd.events;

import com.github.daggerok.ddd.domain.Customer;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Log4j2
@Transactional
@RestController
public class StatisticsResource {

  private final Map<String, AtomicLong> statistics = new ConcurrentHashMap<>();

  @GetMapping("/statistics")
  public ResponseEntity<Map<String, AtomicLong>> getStatistics() {
    return ResponseEntity.ok(statistics);
  }

  @EventListener
  public void on(CustomerCreatedEvent event) {
    log.info("received: {}", event);
    Customer customerCreatedEvent = (Customer) event.getSource();
    String name = customerCreatedEvent.getName();
    statistics.putIfAbsent(name, new AtomicLong(0));
    AtomicLong counter = statistics.get(name);
    counter.incrementAndGet();
    statistics.put(name, counter);
  }
}
