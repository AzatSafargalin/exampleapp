package ru.dev.exampleapp.impl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import ru.dev.exampleapp.impl.etcd.EtcdClientConfiguration;

@Configuration
@Import(value = {ExampleProperties.class, EtcdClientConfiguration.class})
@PropertySource(value = "classpath:exampleapp.properties")
public class ExampleConfiguration {

    @Bean
    ExampleController exampleController(ExampleProperties exampleProperties) {
        return new ExampleController(exampleProperties);
    }


}
