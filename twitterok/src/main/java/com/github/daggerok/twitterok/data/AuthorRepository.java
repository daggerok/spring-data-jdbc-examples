package com.github.daggerok.twitterok.data;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AuthorRepository extends CrudRepository<Author, UUID> {}
