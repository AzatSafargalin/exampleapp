package ru.dev.exampleapp.impl.etcd;

import io.etcd.jetcd.ByteSequence;
import io.etcd.jetcd.Client;
import io.etcd.jetcd.KeyValue;
import io.etcd.jetcd.options.GetOption;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static ru.dev.exampleapp.impl.etcd.EtcdVariables.ETCD_PROPERTY_PREFIX;

public class EtcdManager {

    private final Client client;

    public EtcdManager(Client client) {
        this.client = client;
    }

    public void listen(String key, Consumer<KeyValue> consumer) {
        client.getWatchClient().watch(
                ByteSequence.from(key, StandardCharsets.UTF_8),
                watchResponse -> watchResponse
                                .getEvents()
                                .stream()
                                .forEach(e -> consumer.accept(e.getKeyValue())));
    }

    public Map<String, Object> getPropertiesFromEtcdByPrefix(String prefix) {
        try {
            return client
                    .getKVClient()
                    .get(ByteSequence.from(prefix, StandardCharsets.UTF_8), GetOption.builder().isPrefix(true).build())
                    .get()
                    .getKvs()
                    .stream()
//                    .filter(kv -> EtcdListenParams.ETCD_LISTEN_PARAMS.contains(kv.getKey().toString(StandardCharsets.UTF_8)))
                    .collect(Collectors.toMap(k -> k.getKey().toString(), v -> v.getValue().toString()));
        } catch (Exception e) {
            // todo
        }
        return Collections.emptyMap();
    }
}
