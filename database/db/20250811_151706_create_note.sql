CREATE SCHEMA IF NOT EXISTS noteapp;

create table note (
    id SERIAL PRIMARY KEY,
    name varchar(255)
);