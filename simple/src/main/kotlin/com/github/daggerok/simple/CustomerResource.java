package com.github.daggerok.simple;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import static java.util.Arrays.asList;

@RestController
public class CustomerResource {

  private final CustomerRepository customerRepository;

  public CustomerResource(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  @GetMapping("/find-all")
  public CompletableFuture<Iterable<Customer>> findAll() {
    return CompletableFuture.supplyAsync(customerRepository::findAll);
  }

  @PostMapping
  @Transactional
  public CompletableFuture<Customer> post(@RequestBody Map<String, String> req) {
    String name = Objects.requireNonNull(req.get("name"), "name is required");
    return CompletableFuture.supplyAsync(() -> {
      Customer neeCustomer = Customer.createForName(name);
      return customerRepository.save(neeCustomer);
    });
  }

  @RequestMapping
  public ResponseEntity<?> apiFallback() {
    return ResponseEntity.ok(
        asList(
            "create a customer: http post :8001 name={name}",
            "get all customers: http get  :8001/find-all"
        )
    );
  }
}
