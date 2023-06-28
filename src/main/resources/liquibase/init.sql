-- liquibase formatted sql

-- changeset zikit:1
CREATE TYPE role as enum ('USER', 'ADMIN');

create table "image"
(
    "id"         BIGSERIAL PRIMARY KEY,
    "image"      bytea,
    "media_type" VARCHAR(255)
);

create table "users"
(
    "id_user"      BIGSERIAL PRIMARY KEY,
    "user_name"    VARCHAR(30) NOT NULL,
    "password"     VARCHAR(10485760) NOT NULL,
    "first_name"   VARCHAR(30) NOT NULL,
    "last_name"    VARCHAR(40) NOT NULL,
    "phone"        VARCHAR(30) NOT NULL,
    "role"         role NOT NULL,
    "img_id"       BIGINT
);

create table "ads"
(
    "id_ad"        BIGSERIAL PRIMARY KEY,
    "description"  VARCHAR(255),
    "price"        INTEGER NOT NULL,
    "title"        VARCHAR(255),
    "img_id"       BIGINT,
    "user_id"      BIGINT
);

create table "comments"
(
    id_comment             BIGSERIAL PRIMARY KEY,
    created_at     TIMESTAMP,
    text           VARCHAR(500),
    ads_id         BIGINT,
    user_id        BIGINT
);