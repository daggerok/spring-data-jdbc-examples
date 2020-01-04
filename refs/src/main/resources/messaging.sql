-- h2
-- when books and authors are separated
drop table if exists user;
create table user (
    id bigint identity primary key,
    name varchar(255) not null
);
drop table if exists message;
create table message (
    id bigint identity primary key,
    body varchar(255)
);
drop table if exists user_ref;
create table user_ref (
    message bigint not null,
    user bigint not null,
    constraint message_user_pk primary key (message, user)
);
-- -- hsqldb
-- -- when books and authors are separated
-- create table user (
--     id bigint identity primary key,
--     name varchar(255) not null
-- );
-- create table message (
--     id bigint identity primary key,
--     body varchar(255)
-- );
-- create table user_ref (
--     message bigint not null,
--     user bigint not null,
--     constraint message_user_pk primary key (message, user)
-- );
