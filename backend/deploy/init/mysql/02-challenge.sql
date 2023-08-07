CREATE DATABASE IF NOT EXISTS Challenge;

GRANT ALL ON Challenge.* TO 'user'@'%';

create table Challenge.award
(
    id          varchar(36)                         not null
        primary key,
    created_at  timestamp default CURRENT_TIMESTAMP not null,
    description text                                null,
    name        varchar(250)                        not null,
    team        bit                                 not null,
    visible     bit                                 not null,
    weight      double                              not null,
    end         datetime(6)                         null,
    start       datetime(6)                         null,
    constraint UK_n3547kpsxaq4lpiwfli9cb4gv
        unique (name)
);

create table Challenge.award_submit
(
    id         varchar(36)                         not null
        primary key,
    created_at timestamp default CURRENT_TIMESTAMP not null,
    team_id    varchar(36)                         null,
    user_id    varchar(36)                         not null,
    award_id   varchar(36)                         not null,
    constraint FKi2x3mw2lcii1w42h57ui31ia9
        foreign key (award_id) references Challenge.award (id)
);

create table Challenge.challenge
(
    id          varchar(36)                         not null
        primary key,
    created_at  timestamp default CURRENT_TIMESTAMP not null,
    description text                                null,
    name        varchar(250)                        not null,
    team        bit                                 not null,
    visible     bit                                 not null,
    weight      double                              not null,
    end         datetime(6)                         null,
    flag        varchar(250)                        null,
    start       datetime(6)                         null,
    constraint UK_368j9drdfqrh3jlq24e91ktv8
        unique (name)
);

create table Challenge.challenge_submit
(
    id           varchar(36)                         not null
        primary key,
    created_at   timestamp default CURRENT_TIMESTAMP not null,
    team_id      varchar(36)                         null,
    user_id      varchar(36)                         not null,
    challenge_id varchar(36)                         not null,
    constraint FKqstw5sl9pqg8dlk54ib4wfwoq
        foreign key (challenge_id) references Challenge.challenge (id)
);

create table Challenge.team_score
(
    id         varchar(36)                         not null
        primary key,
    created_at timestamp default CURRENT_TIMESTAMP not null,
    score      double                              not null,
    team_id    varchar(36)                         not null
);

create table Challenge.user_score
(
    id         varchar(36)                         not null
        primary key,
    created_at timestamp default CURRENT_TIMESTAMP not null,
    score      double                              not null,
    user_id    varchar(36)                         not null
);

