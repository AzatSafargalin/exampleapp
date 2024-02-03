package ru.dev.exampleapp.impl;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ping")
public class ExampleController {

    private final ExampleProperties exampleProperties;

    public ExampleController(ExampleProperties exampleProperties) {
        this.exampleProperties = exampleProperties;
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String ping() {
        return exampleProperties.getName();
    }

}
