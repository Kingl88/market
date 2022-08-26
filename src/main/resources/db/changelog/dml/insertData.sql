--liquibase formatted sql

--changeset Siarhei:Insert_data

insert into categories (title)
values ('Meat'),
       ('Fish'),
       ('Vegetable'),
       ('Fruit');

insert into products(title, price, count, category_id)
values ('Peach', 1, 10, 4),
       ('Orange', 2, 10, 4),
       ('Cucumber', 4, 10, 3),
       ('Tomato', 3, 10, 3),
       ('Pear', 6, 10, 4),
       ('Beef', 2, 10, 1),
       ('Ham', 4, 10, 1),
       ('Cod', 3, 10, 2),
       ('Salmon', 6, 10, 2),
       ('Broccoli', 2, 10, 3),
       ('Carrot', 4, 10, 3),
       ('Garlic', 3, 10, 3),
       ('Onion', 6, 10, 3),
       ('Pea', 2, 10, 3),
       ('Apple', 4, 10, 4),
       ('Banana', 3, 10, 4),
       ('Lemon', 6, 10, 4),
       ('Spinach', 4, 10, 3),
       ('Pike', 3, 10, 2),
       ('Turkey', 6, 10, 1),
       ('Chicken', 10, 10, 1);

insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN');

insert into users (username, password, email)
values ('user', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'bob_johnson@gmail.com'),
       ('admin', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'john_johnson@gmail.com');

insert into users_roles (user_id, role_id)
values (1, 1),
       (2, 2);