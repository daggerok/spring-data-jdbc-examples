package com.github.daggerok.twitterok.data;

import java.util.UUID;
import java.util.stream.Stream;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, UUID> {

    @Query(" SELECT * from author ORDER BY at DESC ")
    Stream<Author> findBy();
}
