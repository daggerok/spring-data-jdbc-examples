package com.github.daggerok.simple.onetoone;

import org.springframework.data.repository.CrudRepository;

public interface OneToOneRepository extends CrudRepository<OneToOneAggregateRoot, Long> {}
