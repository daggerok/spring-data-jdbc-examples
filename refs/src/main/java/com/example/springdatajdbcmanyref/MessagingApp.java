package com.example.springdatajdbcmanyref;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.With;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.repository.CrudRepository;

import java.util.Objects;

@With
@Value
@AllArgsConstructor(onConstructor_ = @PersistenceConstructor)
class User {

    @Id
    private final Long id;
    private final String name;
}

interface UserRepository extends CrudRepository<User, Long> {}

@With
@Value
@AllArgsConstructor(onConstructor_ = @PersistenceConstructor)
class Message {

    @Id
    private final Long id;
    private final String body;
    private final UserRef from, to;
}

@Table("user_ref")
@Value(staticConstructor = "of")
class UserRef {
    private final Long user;

    public static UserRef of(User user) {
        Objects.requireNonNull(user, "user may not be null.");
        Objects.requireNonNull(user.getId(), "user ID may not be null.");
        return UserRef.of(user.getId());
    }
}

interface MessageRepository extends CrudRepository<Message, Long> {}

@Configuration
public class MessagingApp { /* stub */ }
