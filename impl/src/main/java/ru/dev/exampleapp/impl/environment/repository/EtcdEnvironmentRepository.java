package ru.dev.exampleapp.impl.environment.repository;

import io.etcd.jetcd.ByteSequence;
import io.etcd.jetcd.Client;
import io.etcd.jetcd.options.GetOption;
import org.springframework.cloud.config.environment.Environment;
import org.springframework.cloud.config.environment.PropertySource;
import org.springframework.cloud.config.server.environment.EnvironmentRepository;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

import static ru.dev.exampleapp.impl.etcd.EtcdVariables.ETCD_PROPERTY_PREFIX;

public class EtcdEnvironmentRepository implements EnvironmentRepository {

    private final Client client;

    public EtcdEnvironmentRepository(Client client) {
        this.client = client;
    }

    @Override
    public Environment findOne(String application, String profile, String label) {
        String[] profiles = StringUtils.commaDelimitedListToStringArray(profile);
        Environment environment = new Environment(application, profiles, null, null, null);
        Map<String, Object> map = getProps();
        PropertySource ps = new PropertySource("etcdProps", map);
        environment.add(ps);
        return environment;
    }

    private Map<String, Object> getProps() {
        try {
            return client
                    .getKVClient()
                    .get(ByteSequence.from(ETCD_PROPERTY_PREFIX, StandardCharsets.UTF_8), GetOption.builder().isPrefix(true).build())
                    .get()
                    .getKvs()
                    .stream()
                    .collect(Collectors.toMap(k -> k.getKey().toString(), v -> v.getValue().toString()));
        } catch (Exception e) {
            // todo
        }
        return Collections.emptyMap();
    }
}
