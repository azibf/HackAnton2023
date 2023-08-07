CREATE DATABASE User;

GRANT ALL ON User.* TO 'user'@'%';

create table User.team
(
    id         varchar(36)                         not null
        primary key,
    created_at timestamp default CURRENT_TIMESTAMP not null,
    invite     varchar(36)                         not null,
    name       varchar(250)                        not null,
    constraint UK_g2l9qqsoeuynt4r5ofdt1x2td
        unique (name)
);

create table User.user
(
    id         varchar(36)                         not null
        primary key,
    created_at timestamp default CURRENT_TIMESTAMP not null,
    admin      bit                                 not null,
    name       varchar(250)                        not null,
    token      varchar(32)                         not null,
    team_id    varchar(36)                         null,
    constraint UK_gj2fy3dcix7ph7k8684gka40c
        unique (name),
    constraint UK_mtqx5podr73c7h25y9qqu96x2
        unique (token),
    constraint FKbmqm8c8m2aw1vgrij7h0od0ok
        foreign key (team_id) references User.team (id)
);

create index IDXmtqx5podr73c7h25y9qqu96x2
    on User.user (token);

create table User.telegram
(
    id          varchar(36)                         not null
        primary key,
    created_at  timestamp default CURRENT_TIMESTAMP not null,
    chat_id     varchar(32)                         not null,
    telegram_id varchar(32)                         not null,
    user_id     varchar(36)                         not null,
    constraint UK_dsxcgmsg67461mfkqq1ghp4gj
        unique (chat_id),
    constraint FKcgj4msa9vse6h3qfwfa57cc90
        foreign key (user_id) references User.user (id)
            on delete cascade
);

create index IDXakrxp7gk6g8iktm6368kulqq6
    on User.telegram (telegram_id);
