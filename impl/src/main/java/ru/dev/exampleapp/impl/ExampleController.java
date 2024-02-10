package ru.dev.exampleapp.impl;

import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.dev.exampleapp.impl.etcd.ExampleProperties;

@RestController
public class ExampleController {

    private final ConfigurableEnvironment environment;

    private final ExampleProperties exampleProperties;

    public ExampleController(
            ConfigurableEnvironment environment,
            ExampleProperties exampleProperties) {
        this.environment = environment;
        this.exampleProperties = exampleProperties;
    }

    @RequestMapping(
            path = "ping",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String ping() {
        return exampleProperties.getName();
    }

}
