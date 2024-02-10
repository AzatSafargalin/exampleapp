# Обновление контекста приложения в рантайме

Для запуска необходимо:
* ETCD Сервер
* Добавить переменную окружения ETCD_HOST=http://127.0.0.1:2379 (после установки ключа необходима перезагрузка Windows)
* Добавить в etcd ключ "example.name" (etcdctl --endpoints=%ETCD_HOST% put example.name value)
* Запустить приложение (javaw -jar ru.dev.exampleapp.main-1.0-SNAPSHOT.jar) либо из IDE
* Получить значение переменной example.name (curl http://127.0.0.1:8080/ping)
* Обновить etcd ключ "example.name" (etcdctl --endpoints=%ETCD_HOST% put example.name newvalue)
* Обновить etcd ключ "example.refresh" (etcdctl --endpoints=%ETCD_HOST% put example.refresh true) -
  обновление данного ключа заставит приложение обновить контекст
* Так же доступен способ обновления контекста приложения через актуатор -
  curl -X POST http://127.0.0.1:8081/actuator/refresh
* Получить новое значение переменной example.name (curl http://127.0.0.1:8080/ping)