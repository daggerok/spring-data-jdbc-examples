-- when books with authors all together
create table author_of_book (
    id   bigint identity primary key,
    name varchar(255) not null
);
create table book_with_authors (
    id    bigint identity primary key,
    title varchar(255)
);
create table author_of_book_ref (
    book_with_authors bigint not null,
    author            bigint not null,
    constraint actor_of_book_ref_pk primary key (book_with_authors, author)
);
