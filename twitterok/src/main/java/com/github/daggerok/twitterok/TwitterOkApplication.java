package com.github.daggerok.twitterok;

import lombok.val;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class TwitterOkApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(TwitterOkApplication.class, args);
        context.registerShutdownHook();
    }
}
