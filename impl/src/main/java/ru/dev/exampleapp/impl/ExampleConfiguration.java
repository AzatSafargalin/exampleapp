package ru.dev.exampleapp.impl;

import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import ru.dev.exampleapp.impl.environment.repository.EtcdRepositoryConfiguration;
import ru.dev.exampleapp.impl.etcd.EtcdClientConfiguration;
import ru.dev.exampleapp.impl.etcd.EtcdManager;
import ru.dev.exampleapp.impl.etcd.ExampleProperties;

import java.util.concurrent.CompletableFuture;

@Configuration
@Import(value = {
        ExampleProperties.class,
        EtcdClientConfiguration.class,
        EtcdRepositoryConfiguration.class})
@PropertySource(value = "classpath:exampleapp.properties")
public class ExampleConfiguration {

    @Bean
    ExampleController exampleController(
            ConfigurableEnvironment environment,
            ExampleProperties exampleProperties) {
        return new ExampleController(environment, exampleProperties);
    }

    @Bean
    boolean subscribeOnUpdate(
            EtcdManager etcdManager,
            ContextRefresher contextRefresher) {
        etcdManager.listen("example.refresh",
                kv -> CompletableFuture.runAsync(
                        () -> System.out.println(String.join(", ", contextRefresher.refresh()))
                )
        );
        return true;
    }

}
