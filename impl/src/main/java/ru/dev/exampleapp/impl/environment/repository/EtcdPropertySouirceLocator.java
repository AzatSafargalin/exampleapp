package ru.dev.exampleapp.impl.environment.repository;

import org.springframework.cloud.bootstrap.config.PropertySourceLocator;
import org.springframework.core.env.CompositePropertySource;
import org.springframework.core.env.Environment;
import org.springframework.cloud.config.environment.PropertySource;
import org.springframework.core.env.MapPropertySource;

import java.util.Map;

import static ru.dev.exampleapp.impl.etcd.EtcdVariables.ETCD_PROPERTY_SOURCE_NAME;

public class EtcdPropertySouirceLocator implements PropertySourceLocator {

    private static final String APPLICATION_NAME = "application-name";

    private static final String SPRING_PROFILES = "none";

    private final EtcdEnvironmentRepository etcdEnvironmentRepository;

    public EtcdPropertySouirceLocator(EtcdEnvironmentRepository etcdEnvironmentRepository) {
        this.etcdEnvironmentRepository = etcdEnvironmentRepository;
    }

    @Override
    public org.springframework.core.env.PropertySource<?> locate(Environment environment) {
        CompositePropertySource composite = new CompositePropertySource(ETCD_PROPERTY_SOURCE_NAME);
        for (PropertySource source : etcdEnvironmentRepository.findOne(APPLICATION_NAME, SPRING_PROFILES, null).getPropertySources()) {
            Map<String, Object> map = (Map<String, Object>) source.getSource();
            // чтобы не переопределять консольные параметры
            map.put("spring.cloud.config.allow-override", "true");
            map.put("spring.cloud.config.override-system-properties", "false");
            composite.addPropertySource(new MapPropertySource(source.getName(), map));
        }
        return composite;
    }
}
