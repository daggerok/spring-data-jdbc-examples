---
DROP TABLE IF EXISTS aggregate_root;
CREATE TABLE aggregate_root (
    id UUID PRIMARY KEY
);
DROP TABLE IF EXISTS aggregate;
CREATE TABLE aggregate (
    aggregate_root_key SERIAL PRIMARY KEY,
    aggregate_root UUID,
    prop VARCHAR NOT NULL,
    value VARCHAR NOT NULL
);
---
DROP TABLE IF EXISTS speaker;
CREATE TABLE speaker (
    id UUID PRIMARY KEY,
    name VARCHAR NOT NULL
);
---
DROP TABLE IF EXISTS session;
CREATE TABLE session (
    id UUID PRIMARY KEY,
    name VARCHAR NOT NULL,
    speakers VARCHAR NOT NULL
);
