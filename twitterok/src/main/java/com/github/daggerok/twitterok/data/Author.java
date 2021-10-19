package com.github.daggerok.twitterok.data;

import java.time.Instant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.With;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;

@With
@Value
@AllArgsConstructor(onConstructor_ = @PersistenceConstructor)
public class Author {

    @Id
    private final UUID id;
    private final String name, username, email;
    private final Instant at;
}

