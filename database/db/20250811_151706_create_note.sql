CREATE SCHEMA IF NOT EXISTS noteapp;

create table IF NOT EXISTS note (
                                    id SERIAL PRIMARY KEY,
                                    name varchar(255) not null
    );