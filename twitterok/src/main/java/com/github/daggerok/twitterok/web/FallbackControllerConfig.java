package com.github.daggerok.twitterok.web;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
public class FallbackControllerConfig implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory>, ErrorController {

    @Override
    public void customize(ConfigurableServletWebServerFactory factory) {
        factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404"),
                              new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/404"));
    }

    @Override
    public String getErrorPath() {
        return "/404";
    }
}
