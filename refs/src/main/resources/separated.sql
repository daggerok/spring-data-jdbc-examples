-- h2
-- when books and authors are separated
drop table if exists author;
create table author (
    id   bigint identity primary key,
    name varchar(255) not null
);
drop table if exists book;
create table book (
    id    bigint identity primary key,
    title varchar(255)
);
-- -- hsqldb
-- -- when books and authors are separated
-- create table author (
--     id   bigint identity primary key,
--     name varchar(255) not null
-- );
-- create table book (
--     id    bigint identity primary key,
--     title varchar(255)
-- );
