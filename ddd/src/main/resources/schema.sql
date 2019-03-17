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
