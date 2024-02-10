package ru.dev.exampleapp.impl.environment.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.dev.exampleapp.impl.etcd.EtcdClientConfiguration;
import ru.dev.exampleapp.impl.etcd.EtcdManager;


@Configuration
@Import(EtcdClientConfiguration.class)
public class EtcdRepositoryConfiguration {

    @Bean
    EtcdEnvironmentRepository etcdEnvironmentRepository(EtcdManager etcdManager) {
        return new EtcdEnvironmentRepository(etcdManager);
    }

    @Bean
    EtcdPropertySouirceLocator etcdPropertySouirceLocator(
            EtcdEnvironmentRepository etcdEnvironmentRepository) {
        return new EtcdPropertySouirceLocator(etcdEnvironmentRepository);
    }

}
