package ru.dev.exampleapp.impl.environment.repository;

import io.etcd.jetcd.Client;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.dev.exampleapp.impl.etcd.EtcdClientConfiguration;

@Configuration
@Import(EtcdClientConfiguration.class)
public class EtcdRepositoryConfiguration {

    @Bean
    EtcdEnvironmentRepositoryFactory etcd(Client client) {
        return new EtcdEnvironmentRepositoryFactory(client);
    }
}
