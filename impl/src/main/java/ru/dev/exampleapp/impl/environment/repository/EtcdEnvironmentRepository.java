package ru.dev.exampleapp.impl.environment.repository;

import org.springframework.cloud.config.environment.Environment;
import org.springframework.cloud.config.environment.PropertySource;
import org.springframework.cloud.config.server.environment.EnvironmentRepository;
import org.springframework.util.StringUtils;
import ru.dev.exampleapp.impl.etcd.EtcdListenParams;
import ru.dev.exampleapp.impl.etcd.EtcdManager;

import java.util.Map;
import java.util.stream.Collectors;

import static ru.dev.exampleapp.impl.etcd.EtcdVariables.ETCD_PROPERTY_PREFIX;
import static ru.dev.exampleapp.impl.etcd.EtcdVariables.ETCD_PROPERTY_SOURCE_NAME;

public class EtcdEnvironmentRepository implements EnvironmentRepository {

    private final EtcdManager etcdManager;

    public EtcdEnvironmentRepository(EtcdManager etcdManager) {
        this.etcdManager = etcdManager;
    }

    @Override
    public Environment findOne(String application, String profile, String label) {
        String[] profiles = StringUtils.commaDelimitedListToStringArray(profile);
        Environment environment = new Environment(application, profiles, null, null, null);
        Map<String, Object> map = etcdManager.getPropertiesFromEtcdByPrefix(ETCD_PROPERTY_PREFIX)
                .entrySet()
                .stream()
                .filter(v -> EtcdListenParams.ETCD_LISTEN_PARAMS.contains(v.getKey()))
                .collect(Collectors.toMap(k -> k.getKey(), v -> v.getValue()));
        PropertySource ps = new PropertySource(ETCD_PROPERTY_SOURCE_NAME, map);
        environment.add(ps);
        return environment;
    }
}
