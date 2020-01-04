-- h2
-- when books with authors all together
drop table if exists author_of_book;
create table author_of_book (
    id   bigint identity primary key,
    name varchar(255) not null
);
drop table if exists book_with_authors;
create table book_with_authors (
    id    bigint identity primary key,
    title varchar(255)
);
drop table if exists author_of_book_ref;
create table author_of_book_ref (
    book_with_authors bigint not null,
    author            bigint not null,
    constraint actor_of_book_ref_pk primary key (book_with_authors, author)
);
-- -- hsqldb
-- -- when books with authors all together
-- create table author_of_book (
--     id   bigint identity primary key,
--     name varchar(255) not null
-- );
-- create table book_with_authors (
--     id    bigint identity primary key,
--     title varchar(255)
-- );
-- create table author_of_book_ref (
--     book_with_authors bigint not null,
--     author            bigint not null,
--     constraint actor_of_book_ref_pk primary key (book_with_authors, author)
-- );
