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
    id              bigserial primary key,
    price_per_unit  decimal,
    count           int,
    total_price     decimal,
    product_id      bigint references products (id),
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp
);

create table users
(
    id         bigserial primary key,
    username   varchar(30) unique,
    password   varchar(80) not null,
    email      varchar(50) unique,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table orders
(
    id              bigserial primary key,
    order_status    varchar(255),
    user_id         bigint references users (id),
    item_id         bigint references items (id),
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp
);

create table profiles(
    id          bigserial primary key,
    name        varchar(255),
    surname     varchar(255),
    phone       varchar(255),
    address     varchar(255),
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp

);

create table roles
(
    id         bigserial primary key,
    name       varchar(50) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table users_roles
(
    user_id bigint not null references users (id),
    role_id bigint not null references roles (id),
    primary key (user_id, role_id)
);
