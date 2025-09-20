CREATE SCHEMA IF NOT EXISTS noteapp;

create table IF NOT EXISTS notes (
                                    id BIGSERIAL PRIMARY KEY,
                                    title varchar(255) not null,
                                    content varchar(1000) not null,
                                    priority varchar(10) not null default 'MEDIUM',
                                    created_at timestamp not null default CURRENT_TIMESTAMP,
                                    updated_at timestamp default null,

                                    CONSTRAINT chk_priority CHECK (priority IN ('LOW', 'MEDIUM', 'HIGH', 'URGENT'))

    );