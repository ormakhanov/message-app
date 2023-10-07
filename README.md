Чтобы запустить:
1. запустите docker-compose.yml для БД - "docker-compose up -d"
2. запустите zk-single-kafka-single.yml для Kafka - "docker compose -f zk-single-kafka-single.yml up"
3. запустите микросервисы

Откройте Swagger-ui:
Receive message - http://localhost:8093/swagger-ui.html
Send message - http://localhost:8094/swagger-ui.html

Endpoints:
1. POST http://localhost:8093/api/messages - Отправим сообщение
Пример message application/xml:
<?xml version="1.0" encoding="UTF-8"?>
<MessagesEntity>
    <sender>Askar</sender>
    <messageContent>Hello, World!</messageContent>
</MessagesEntity>

2. GET http://localhost:8093/api/messages?user=Askar - Возвращает сообщений по пользователю, если передан и если не передан, то последние 10 сообщений

3. GET http://localhost:8094/api/responses?user=Askar - Возвращает список отправленных сообщений и код ответа

