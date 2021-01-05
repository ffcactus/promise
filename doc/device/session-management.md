# Session管理

## session的记录

只有collector完成了注册，它的Session才能被使用。因此session被记录发生在collector注册之后。 流程如下:

- WebsocketHandler -- 监测到session建立。
- Exchanger -- 分发collector注册命令。
- CollectorRegisterAction -- 处理collector注册命令。
- CollectorService -- 处理collector注册。
    - RecordedCollectorRepository -- 更新数据库中的Collector的信息。
    - SessionEventPublisher -- 发布session消息。
        - CollectorSessionRepository -- 接收session建立的消息，将Session缓存。

## session的失效

- WebsocketHandler -- 监测到session丢失。
-
- CollectorService -- 处理collector丢失。
