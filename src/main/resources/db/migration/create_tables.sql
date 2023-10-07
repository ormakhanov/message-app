create table messages
(
    id           bigserial primary key,
    sender       varchar(100) not null,
    message_content varchar,
    datetime timestamp
);

create table responses
(
    id     bigserial primary key,
    message_id         bigint references messages (id),
    response_code varchar
);


