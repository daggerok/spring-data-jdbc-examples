package com.example.springboot22;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AggregateRootRepository extends CrudRepository<AggregateRoot, UUID> { }
