package ru.dev.exampleapp.impl.etcd;

import java.util.Set;

public final class EtcdListenParams {

    private EtcdListenParams() {
    }

    /**
     * Подписываемся только на обновление этих параметров.
     */
    public static Set<String> ETCD_LISTEN_PARAMS = Set.of(
            "example.name",
            "example.test"
    );
}
