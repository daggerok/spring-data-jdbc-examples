package com.example.springboot22;

import lombok.Value;
import lombok.With;
import org.springframework.data.annotation.Id;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@With
@Value
// @Table("session")
public class Session {

    @Id
    private final UUID id;
    private final String name;
    private final String speakers;

    public Collection<String> toUuid() {
        Objects.requireNonNull(speakers);
        return Arrays.stream(speakers.split(","))
                     .map(String::trim)
                     .collect(Collectors.toList());

    }
}
