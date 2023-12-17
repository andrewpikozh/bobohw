CREATE TABLE IF NOT EXISTS pictures
(
    id         BIGINT,
    nasa_id    BIGINT,
    img_src    VARCHAR(255),
    camera_id  BIGINT,
    created_at TIMESTAMP,
    primary key (id),
    foreign key (camera_id) references cameras(id)
);