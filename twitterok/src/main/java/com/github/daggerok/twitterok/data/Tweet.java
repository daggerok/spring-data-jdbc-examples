package com.github.daggerok.twitterok.data;

import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.With;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

@With
@Value
@AllArgsConstructor(onConstructor_ = @PersistenceConstructor)
public class Tweet {

    @Id
    private final UUID id;
    private final String message;
    private final AuthorRef author;
}

