package com.github.daggerok.twitterok.web.user;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Data
@Component
@Scope(SCOPE_PROTOTYPE)
public class UserModel {
    @NotBlank(message = "fields is required")
    private String name, username, email;
}
