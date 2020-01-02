CREATE SCHEMA IF NOT EXISTS public;

DROP TABLE IF EXISTS message_author;
DROP TABLE IF EXISTS message;
DROP TABLE IF EXISTS author;

CREATE TABLE IF NOT EXISTS message(
  id UUID NOT NULL DEFAULT RANDOM_UUID(),
  body VARCHAR(4096) NOT NULL,
  aggregate_id UUID NOT NULL AS RANDOM_UUID(),
  last_modified_date_time TIMESTAMP NOT NULL AS NOW(),
  CONSTRAINT message_pk PRIMARY KEY ( id )
);

CREATE TABLE IF NOT EXISTS author(
  id UUID NOT NULL DEFAULT RANDOM_UUID(),
  name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  last_modified_date_time TIMESTAMP NOT NULL AS NOW(),
  CONSTRAINT author_pk PRIMARY KEY ( id )
);

CREATE TABLE IF NOT EXISTS message_author (
    message UUID,
    author UUID,
    CONSTRAINT message_author_pk PRIMARY KEY ( message, author )
);
