package com.github.daggerok.twitterok.data;

import lombok.RequiredArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table("author_ref")
@RequiredArgsConstructor
public class AuthorRef {
    private final UUID author;
}
