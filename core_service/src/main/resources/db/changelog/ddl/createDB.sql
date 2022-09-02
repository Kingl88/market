--liquibase formatted sql

--changeset Siarhei:Create_tables

create table categories
(
    id    bigserial primary key,
    title varchar(255)
);

create table products
(
    id          bigserial primary key,
    title       varchar(255),
    price       int,
    count       int,
    category_id bigint references categories (id)
);

create table items
(
    id             bigserial primary key,
    price_per_unit decimal,
    count          int,
    total_price    decimal,
    product_id     bigint references products (id),
    created_at     timestamp default current_timestamp,
    updated_at     timestamp default current_timestamp
);

create table orders
(
    id           bigserial primary key,
    order_status varchar(255) not null ,
    username     varchar(255),
    item_id      bigint references items (id),
    created_at   timestamp default current_timestamp,
    updated_at   timestamp default current_timestamp
);

create table profiles
(
    id         bigserial primary key,
    name       varchar(255),
    surname    varchar(255),
    phone      varchar(255),
    address    varchar(255),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp

);
