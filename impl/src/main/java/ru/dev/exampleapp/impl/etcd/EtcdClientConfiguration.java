package ru.dev.exampleapp.impl.etcd;

import io.etcd.jetcd.Client;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static ru.dev.exampleapp.impl.etcd.EtcdVariables.ETCD_HOST;

@Configuration
public class EtcdClientConfiguration {

    @Bean
    Client client() {
        return Client.builder().endpoints(System.getenv(ETCD_HOST)).build();
    }

    @Bean
    EtcdManager etcdListener(Client client) {
        return new EtcdManager(client);
    }
}
