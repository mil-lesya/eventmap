create table "user"
(
    id      bigserial not null
        constraint user_pk
            primary key,
    name    varchar   not null,
    surname varchar   not null,
    email   varchar   not null,
    hash    varchar   not null
);

alter table "user"
    owner to postgres;

create unique index user_mail_uindex
    on "user" (email);

create table event
(
    id          bigserial not null
        constraint event_pk
            primary key,
    date        timestamp not null,
    description text,
    price       integer,
    name        varchar(20)
);

alter table event
    owner to postgres;

create table visited_event
(
    id         bigserial not null
        constraint visited_event_pk
            primary key,
    event_id   bigint    not null
        constraint visited_event_event_id_fk
            references event,
    user_id    bigint    not null
        constraint visited_event_user_id_fk
            references "user",
    assessment boolean
);

alter table visited_event
    owner to postgres;

create table token
(
    id      bigserial not null
        constraint token_pk
            primary key,
    user_id bigint    not null
        constraint token_user_id_fk
            references "user",
    token   varchar   not null
);

alter table token
    owner to postgres;

