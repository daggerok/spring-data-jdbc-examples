-- when books and authors are separated
create table author (
    id   bigint identity primary key,
    name varchar(255) not null
);
create table book (
    id    bigint identity primary key,
    title varchar(255)
);
