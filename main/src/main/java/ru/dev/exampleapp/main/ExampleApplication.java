package ru.dev.exampleapp.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import ru.dev.exampleapp.impl.ExampleConfiguration;

@SpringBootApplication
@Import(ExampleConfiguration.class)
public class ExampleApplication {

    public static void main(String... args) {
        SpringApplication.run(ExampleApplication.class, args);
    }
}
