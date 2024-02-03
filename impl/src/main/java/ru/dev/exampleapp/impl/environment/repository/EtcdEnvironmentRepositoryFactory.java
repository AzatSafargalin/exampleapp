package ru.dev.exampleapp.impl.environment.repository;

import io.etcd.jetcd.Client;
import org.springframework.cloud.config.server.environment.EnvironmentRepositoryFactory;

public class EtcdEnvironmentRepositoryFactory implements
        EnvironmentRepositoryFactory<EtcdEnvironmentRepository, EtcdEnvironmentRepositoryProperties> {

    private final Client client;

    public EtcdEnvironmentRepositoryFactory(Client client) {
        this.client = client;
    }

    @Override
    public EtcdEnvironmentRepository build(EtcdEnvironmentRepositoryProperties environmentProperties) {
        return new EtcdEnvironmentRepository(client);
    }
}
