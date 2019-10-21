package com.example.springboot22;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface SpeakersRepository extends CrudRepository<Speaker, UUID> { }
