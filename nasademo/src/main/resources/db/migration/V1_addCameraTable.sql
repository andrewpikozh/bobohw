CREATE TABLE IF NOT EXISTS cameras(
    id BIGINT not null,
    nasa_id BIGINT,
    name VARCHAR(255),
    created_at timestamp,
    primary key (id)
);