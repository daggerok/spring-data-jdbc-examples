package com.example.springboot22;

import lombok.Value;
import lombok.With;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@With
@Value
// @Table("speaker")
public class Speaker {

    @Id
    private final UUID id;
    private final String name;
}
