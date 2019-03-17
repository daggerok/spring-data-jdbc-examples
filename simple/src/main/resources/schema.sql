-- this fine is going to be executed because ot's location is classpath:/schema.sql (DDL)
-- same will be for classpath:/data.sql (DML)

drop table if exists customer;
drop table if exists gender;

create table customer (
  id bigserial not null primary key,
  name varchar(255) not null,
  date date,
);

create table gender (
  id bigserial not null primary key,
  who_cares varchar(255),
  customer bigint not null,
);

-- --------------------------------------------

drop table if exists ONE_TO_ONE_SET_REF;
drop table if exists ONE_TO_ONE_AGGREGATE_ROOT;

create table ONE_TO_ONE_AGGREGATE_ROOT (
  ID bigserial not null primary key,
);

create table ONE_TO_ONE_SET_REF (
  ONE_TO_ONE_AGGREGATE_ROOT bigint,
  NAME varchar(255),
);

-- --------------------------------------------

drop table if exists Book;
drop table if exists Author;
drop table if exists Book_Author;

create table Book (
  id bigserial not null primary key,
  title varchar(255),
);

create table Author (
  id bigserial not null primary key,
  name varchar(255),
);

create table Book_Author (
  Book bigint, -- references Book(id),
  Author bigint, -- references Author(id),
);

-- --------------------------------------------

drop table if exists some_entity_with_map;
drop table if exists entity_which_is_a_map_value;

create table some_entity_with_map (
  id bigserial not null primary key,
  -- name varchar(255),
  -- ...
);

create table entity_which_is_a_map_value (
  some_entity_with_map bigint,
  some_entity_with_map_key varchar(255),
  -- Street varchar(255),
  -- City varchar(255),
  -- ...
);
