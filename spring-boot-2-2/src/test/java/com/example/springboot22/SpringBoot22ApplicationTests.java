package com.example.springboot22;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@Log4j2
@SpringBootTest
@WithTestProfile
@AllArgsConstructor(onConstructor_ = @Autowired)
class SpringBoot22ApplicationTests {

    SpeakersRepository speakersRepository;
    SessionsRepository sessionsRepository;
    AggregateRootRepository aggregateRootRepository;

    @Test
    void aggregate_root_should_works_fine() {
        Iterable<AggregateRoot> allRoots = aggregateRootRepository.findAll();
        allRoots.forEach(log::info);
        assertThat(allRoots).isNotNull();
    }

    @Test
    void speakers_also_should_works() {
        val allSpeakers = speakersRepository.findAll();
        allSpeakers.forEach(log::info);
        assertThat(allSpeakers).hasSize(3);
    }

    @Test
    void finally_sessions_should_works_too() {
        val allSessions = sessionsRepository.findAll();
        allSessions.forEach(log::info);
        assertThat(allSessions).hasSize(2);
    }
}
