package ru.dev.exampleapp.impl.environment.repository;

import org.springframework.cloud.config.server.support.EnvironmentRepositoryProperties;

public class EtcdEnvironmentRepositoryProperties implements EnvironmentRepositoryProperties {

    @Override
    public void setOrder(int order) {

    }
}
