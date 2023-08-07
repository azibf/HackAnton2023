CREATE DATABASE IF NOT EXISTS Event;

GRANT ALL ON Event.* TO 'user'@'%';

create table Event.event
(
    id         varchar(36)                         not null
        primary key,
    created_at timestamp default CURRENT_TIMESTAMP not null,
    description text                               null,
    affiliate  varchar(250)                        null,
    end        datetime(6)                         not null,
    speaker    varchar(250)                        null,
    start      datetime(6)                         not null,
    title      varchar(500)                        not null
);
