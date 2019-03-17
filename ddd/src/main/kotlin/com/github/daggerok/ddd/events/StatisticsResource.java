package com.github.daggerok.ddd.events;

import com.github.daggerok.ddd.domain.Customer;
import com.github.daggerok.ddd.domain.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.StreamSupport;

@Log4j2
@RestController
@RequiredArgsConstructor
public class StatisticsResource {

  private Map<String, AtomicLong> statistics = new ConcurrentHashMap<>();

  private final CustomerRepository customerRepository;

  @PostConstruct
  public void reconstruct() {
    StreamSupport.stream(customerRepository.findAll().spliterator(), true)
                 .forEach(this::updateStatistics);
  }

  @GetMapping("/statistics")
  public ResponseEntity<Map<String, AtomicLong>> getStatistics() {
    return ResponseEntity.ok(statistics);
  }

  @EventListener
  public void on(CustomerCreatedEvent event) {
    log.info("received: {}", event);
    Customer customer = (Customer) event.getSource();
    updateStatistics(customer);
  }

  private void updateStatistics(Customer customer) {
    String name = customer.getName();
    statistics.putIfAbsent(name, new AtomicLong(0));
    AtomicLong counter = statistics.get(name);
    counter.incrementAndGet();
    statistics.put(name, counter);
  }
}
